import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEstadisticaAjusteNeuronorma } from 'app/shared/model/estadistica-ajuste-neuronorma.model';
import { EstadisticaAjusteNeuronormaService } from './estadistica-ajuste-neuronorma.service';

@Component({
  templateUrl: './estadistica-ajuste-neuronorma-delete-dialog.component.html'
})
export class EstadisticaAjusteNeuronormaDeleteDialogComponent {
  estadisticaAjusteNeuronorma?: IEstadisticaAjusteNeuronorma;

  constructor(
    protected estadisticaAjusteNeuronormaService: EstadisticaAjusteNeuronormaService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.estadisticaAjusteNeuronormaService.delete(id).subscribe(() => {
      this.eventManager.broadcast('estadisticaAjusteNeuronormaListModification');
      this.activeModal.close();
    });
  }
}
