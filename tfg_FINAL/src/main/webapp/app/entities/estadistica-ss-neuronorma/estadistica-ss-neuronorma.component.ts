import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEstadisticaSSNeuronorma } from 'app/shared/model/estadistica-ss-neuronorma.model';
import { EstadisticaSSNeuronormaService } from './estadistica-ss-neuronorma.service';
import { EstadisticaSSNeuronormaDeleteDialogComponent } from './estadistica-ss-neuronorma-delete-dialog.component';

@Component({
  selector: 'jhi-estadistica-ss-neuronorma',
  templateUrl: './estadistica-ss-neuronorma.component.html'
})
export class EstadisticaSSNeuronormaComponent implements OnInit, OnDestroy {
  estadisticaSSNeuronormas?: IEstadisticaSSNeuronorma[];
  eventSubscriber?: Subscription;

  constructor(
    protected estadisticaSSNeuronormaService: EstadisticaSSNeuronormaService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.estadisticaSSNeuronormaService.query().subscribe((res: HttpResponse<IEstadisticaSSNeuronorma[]>) => {
      this.estadisticaSSNeuronormas = res.body ? res.body : [];
    });
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInEstadisticaSSNeuronormas();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IEstadisticaSSNeuronorma): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInEstadisticaSSNeuronormas(): void {
    this.eventSubscriber = this.eventManager.subscribe('estadisticaSSNeuronormaListModification', () => this.loadAll());
  }

  delete(estadisticaSSNeuronorma: IEstadisticaSSNeuronorma): void {
    const modalRef = this.modalService.open(EstadisticaSSNeuronormaDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.estadisticaSSNeuronorma = estadisticaSSNeuronorma;
  }
}
