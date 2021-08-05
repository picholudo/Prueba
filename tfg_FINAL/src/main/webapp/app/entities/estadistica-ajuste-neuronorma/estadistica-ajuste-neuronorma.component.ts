import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEstadisticaAjusteNeuronorma } from 'app/shared/model/estadistica-ajuste-neuronorma.model';
import { EstadisticaAjusteNeuronormaService } from './estadistica-ajuste-neuronorma.service';
import { EstadisticaAjusteNeuronormaDeleteDialogComponent } from './estadistica-ajuste-neuronorma-delete-dialog.component';

@Component({
  selector: 'jhi-estadistica-ajuste-neuronorma',
  templateUrl: './estadistica-ajuste-neuronorma.component.html'
})
export class EstadisticaAjusteNeuronormaComponent implements OnInit, OnDestroy {
  estadisticaAjusteNeuronormas?: IEstadisticaAjusteNeuronorma[];
  eventSubscriber?: Subscription;

  constructor(
    protected estadisticaAjusteNeuronormaService: EstadisticaAjusteNeuronormaService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.estadisticaAjusteNeuronormaService.query().subscribe((res: HttpResponse<IEstadisticaAjusteNeuronorma[]>) => {
      this.estadisticaAjusteNeuronormas = res.body ? res.body : [];
    });
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInEstadisticaAjusteNeuronormas();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IEstadisticaAjusteNeuronorma): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInEstadisticaAjusteNeuronormas(): void {
    this.eventSubscriber = this.eventManager.subscribe('estadisticaAjusteNeuronormaListModification', () => this.loadAll());
  }

  delete(estadisticaAjusteNeuronorma: IEstadisticaAjusteNeuronorma): void {
    const modalRef = this.modalService.open(EstadisticaAjusteNeuronormaDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.estadisticaAjusteNeuronorma = estadisticaAjusteNeuronorma;
  }
}
