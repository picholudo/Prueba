import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEstadisticaFAB } from 'app/shared/model/estadistica-fab.model';
import { EstadisticaFABService } from './estadistica-fab.service';
import { EstadisticaFABDeleteDialogComponent } from './estadistica-fab-delete-dialog.component';

@Component({
  selector: 'jhi-estadistica-fab',
  templateUrl: './estadistica-fab.component.html'
})
export class EstadisticaFABComponent implements OnInit, OnDestroy {
  estadisticaFABS?: IEstadisticaFAB[];
  eventSubscriber?: Subscription;

  constructor(
    protected estadisticaFABService: EstadisticaFABService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.estadisticaFABService.query().subscribe((res: HttpResponse<IEstadisticaFAB[]>) => {
      this.estadisticaFABS = res.body ? res.body : [];
    });
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInEstadisticaFABS();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IEstadisticaFAB): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInEstadisticaFABS(): void {
    this.eventSubscriber = this.eventManager.subscribe('estadisticaFABListModification', () => this.loadAll());
  }

  delete(estadisticaFAB: IEstadisticaFAB): void {
    const modalRef = this.modalService.open(EstadisticaFABDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.estadisticaFAB = estadisticaFAB;
  }
}
