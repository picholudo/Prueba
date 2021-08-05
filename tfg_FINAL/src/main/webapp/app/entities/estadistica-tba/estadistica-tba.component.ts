import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEstadisticaTBA } from 'app/shared/model/estadistica-tba.model';
import { EstadisticaTBAService } from './estadistica-tba.service';
import { EstadisticaTBADeleteDialogComponent } from './estadistica-tba-delete-dialog.component';

@Component({
  selector: 'jhi-estadistica-tba',
  templateUrl: './estadistica-tba.component.html'
})
export class EstadisticaTBAComponent implements OnInit, OnDestroy {
  estadisticaTBAS?: IEstadisticaTBA[];
  eventSubscriber?: Subscription;

  constructor(
    protected estadisticaTBAService: EstadisticaTBAService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.estadisticaTBAService.query().subscribe((res: HttpResponse<IEstadisticaTBA[]>) => {
      this.estadisticaTBAS = res.body ? res.body : [];
    });
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInEstadisticaTBAS();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IEstadisticaTBA): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInEstadisticaTBAS(): void {
    this.eventSubscriber = this.eventManager.subscribe('estadisticaTBAListModification', () => this.loadAll());
  }

  delete(estadisticaTBA: IEstadisticaTBA): void {
    const modalRef = this.modalService.open(EstadisticaTBADeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.estadisticaTBA = estadisticaTBA;
  }
}
