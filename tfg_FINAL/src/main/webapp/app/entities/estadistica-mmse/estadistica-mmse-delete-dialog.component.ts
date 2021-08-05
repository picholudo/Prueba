import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEstadisticaMMSE } from 'app/shared/model/estadistica-mmse.model';
import { EstadisticaMMSEService } from './estadistica-mmse.service';

@Component({
  templateUrl: './estadistica-mmse-delete-dialog.component.html'
})
export class EstadisticaMMSEDeleteDialogComponent {
  estadisticaMMSE?: IEstadisticaMMSE;

  constructor(
    protected estadisticaMMSEService: EstadisticaMMSEService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.estadisticaMMSEService.delete(id).subscribe(() => {
      this.eventManager.broadcast('estadisticaMMSEListModification');
      this.activeModal.close();
    });
  }
}
