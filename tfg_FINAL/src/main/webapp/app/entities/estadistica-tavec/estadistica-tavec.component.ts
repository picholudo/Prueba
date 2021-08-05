import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEstadisticaTAVEC } from 'app/shared/model/estadistica-tavec.model';
import { EstadisticaTAVECService } from './estadistica-tavec.service';
import { EstadisticaTAVECDeleteDialogComponent } from './estadistica-tavec-delete-dialog.component';

@Component({
  selector: 'jhi-estadistica-tavec',
  templateUrl: './estadistica-tavec.component.html'
})
export class EstadisticaTAVECComponent implements OnInit, OnDestroy {
  estadisticaTAVECS?: IEstadisticaTAVEC[];
  eventSubscriber?: Subscription;

  constructor(
    protected estadisticaTAVECService: EstadisticaTAVECService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.estadisticaTAVECService.query().subscribe((res: HttpResponse<IEstadisticaTAVEC[]>) => {
      this.estadisticaTAVECS = res.body ? res.body : [];
    });
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInEstadisticaTAVECS();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IEstadisticaTAVEC): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInEstadisticaTAVECS(): void {
    this.eventSubscriber = this.eventManager.subscribe('estadisticaTAVECListModification', () => this.loadAll());
  }

  delete(estadisticaTAVEC: IEstadisticaTAVEC): void {
    const modalRef = this.modalService.open(EstadisticaTAVECDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.estadisticaTAVEC = estadisticaTAVEC;
  }
}
