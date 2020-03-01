import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEstadisticas } from 'app/shared/model/estadisticas.model';
import { EstadisticasService } from './estadisticas.service';
import { EstadisticasDeleteDialogComponent } from './estadisticas-delete-dialog.component';

@Component({
  selector: 'jhi-estadisticas',
  templateUrl: './estadisticas.component.html'
})
export class EstadisticasComponent implements OnInit, OnDestroy {
  estadisticas?: IEstadisticas[];
  eventSubscriber?: Subscription;

  constructor(
    protected estadisticasService: EstadisticasService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.estadisticasService.query().subscribe((res: HttpResponse<IEstadisticas[]>) => {
      this.estadisticas = res.body ? res.body : [];
    });
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInEstadisticas();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IEstadisticas): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInEstadisticas(): void {
    this.eventSubscriber = this.eventManager.subscribe('estadisticasListModification', () => this.loadAll());
  }

  delete(estadisticas: IEstadisticas): void {
    const modalRef = this.modalService.open(EstadisticasDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.estadisticas = estadisticas;
  }
}
