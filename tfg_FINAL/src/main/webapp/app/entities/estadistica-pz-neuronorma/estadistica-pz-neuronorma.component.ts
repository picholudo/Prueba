import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEstadisticaPzNeuronorma } from 'app/shared/model/estadistica-pz-neuronorma.model';
import { EstadisticaPzNeuronormaService } from './estadistica-pz-neuronorma.service';
import { EstadisticaPzNeuronormaDeleteDialogComponent } from './estadistica-pz-neuronorma-delete-dialog.component';

@Component({
  selector: 'jhi-estadistica-pz-neuronorma',
  templateUrl: './estadistica-pz-neuronorma.component.html'
})
export class EstadisticaPzNeuronormaComponent implements OnInit, OnDestroy {
  estadisticaPzNeuronormas?: IEstadisticaPzNeuronorma[];
  eventSubscriber?: Subscription;

  constructor(
    protected estadisticaPzNeuronormaService: EstadisticaPzNeuronormaService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.estadisticaPzNeuronormaService.query().subscribe((res: HttpResponse<IEstadisticaPzNeuronorma[]>) => {
      this.estadisticaPzNeuronormas = res.body ? res.body : [];
    });
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInEstadisticaPzNeuronormas();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IEstadisticaPzNeuronorma): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInEstadisticaPzNeuronormas(): void {
    this.eventSubscriber = this.eventManager.subscribe('estadisticaPzNeuronormaListModification', () => this.loadAll());
  }

  delete(estadisticaPzNeuronorma: IEstadisticaPzNeuronorma): void {
    const modalRef = this.modalService.open(EstadisticaPzNeuronormaDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.estadisticaPzNeuronorma = estadisticaPzNeuronorma;
  }
}
