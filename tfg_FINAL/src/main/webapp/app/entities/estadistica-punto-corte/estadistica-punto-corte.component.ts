import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEstadisticaPuntoCorte } from 'app/shared/model/estadistica-punto-corte.model';
import { EstadisticaPuntoCorteService } from './estadistica-punto-corte.service';
import { EstadisticaPuntoCorteDeleteDialogComponent } from './estadistica-punto-corte-delete-dialog.component';

@Component({
  selector: 'jhi-estadistica-punto-corte',
  templateUrl: './estadistica-punto-corte.component.html'
})
export class EstadisticaPuntoCorteComponent implements OnInit, OnDestroy {
  estadisticaPuntoCortes?: IEstadisticaPuntoCorte[];
  eventSubscriber?: Subscription;

  constructor(
    protected estadisticaPuntoCorteService: EstadisticaPuntoCorteService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.estadisticaPuntoCorteService.query().subscribe((res: HttpResponse<IEstadisticaPuntoCorte[]>) => {
      this.estadisticaPuntoCortes = res.body ? res.body : [];
    });
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInEstadisticaPuntoCortes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IEstadisticaPuntoCorte): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInEstadisticaPuntoCortes(): void {
    this.eventSubscriber = this.eventManager.subscribe('estadisticaPuntoCorteListModification', () => this.loadAll());
  }

  delete(estadisticaPuntoCorte: IEstadisticaPuntoCorte): void {
    const modalRef = this.modalService.open(EstadisticaPuntoCorteDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.estadisticaPuntoCorte = estadisticaPuntoCorte;
  }
}
