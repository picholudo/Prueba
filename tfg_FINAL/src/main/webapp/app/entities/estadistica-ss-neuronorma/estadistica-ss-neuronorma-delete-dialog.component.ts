import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEstadisticaSSNeuronorma } from 'app/shared/model/estadistica-ss-neuronorma.model';
import { EstadisticaSSNeuronormaService } from './estadistica-ss-neuronorma.service';

@Component({
  templateUrl: './estadistica-ss-neuronorma-delete-dialog.component.html'
})
export class EstadisticaSSNeuronormaDeleteDialogComponent {
  estadisticaSSNeuronorma?: IEstadisticaSSNeuronorma;

  constructor(
    protected estadisticaSSNeuronormaService: EstadisticaSSNeuronormaService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.estadisticaSSNeuronormaService.delete(id).subscribe(() => {
      this.eventManager.broadcast('estadisticaSSNeuronormaListModification');
      this.activeModal.close();
    });
  }
}
