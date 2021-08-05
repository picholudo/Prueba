import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEstadisticaMMSE } from 'app/shared/model/estadistica-mmse.model';
import { EstadisticaMMSEService } from './estadistica-mmse.service';
import { EstadisticaMMSEDeleteDialogComponent } from './estadistica-mmse-delete-dialog.component';

@Component({
  selector: 'jhi-estadistica-mmse',
  templateUrl: './estadistica-mmse.component.html'
})
export class EstadisticaMMSEComponent implements OnInit, OnDestroy {
  estadisticaMMSES?: IEstadisticaMMSE[];
  eventSubscriber?: Subscription;

  constructor(
    protected estadisticaMMSEService: EstadisticaMMSEService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.estadisticaMMSEService.query().subscribe((res: HttpResponse<IEstadisticaMMSE[]>) => {
      this.estadisticaMMSES = res.body ? res.body : [];
    });
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInEstadisticaMMSES();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IEstadisticaMMSE): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInEstadisticaMMSES(): void {
    this.eventSubscriber = this.eventManager.subscribe('estadisticaMMSEListModification', () => this.loadAll());
  }

  delete(estadisticaMMSE: IEstadisticaMMSE): void {
    const modalRef = this.modalService.open(EstadisticaMMSEDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.estadisticaMMSE = estadisticaMMSE;
  }
}
