import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEstadisticaPzNeuronorma } from 'app/shared/model/estadistica-pz-neuronorma.model';
import { EstadisticaPzNeuronormaService } from './estadistica-pz-neuronorma.service';

@Component({
  templateUrl: './estadistica-pz-neuronorma-delete-dialog.component.html'
})
export class EstadisticaPzNeuronormaDeleteDialogComponent {
  estadisticaPzNeuronorma?: IEstadisticaPzNeuronorma;

  constructor(
    protected estadisticaPzNeuronormaService: EstadisticaPzNeuronormaService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.estadisticaPzNeuronormaService.delete(id).subscribe(() => {
      this.eventManager.broadcast('estadisticaPzNeuronormaListModification');
      this.activeModal.close();
    });
  }
}
