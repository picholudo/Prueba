import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEstadisticas } from 'app/shared/model/estadisticas.model';
import { EstadisticasService } from './estadisticas.service';

@Component({
  templateUrl: './estadisticas-delete-dialog.component.html'
})
export class EstadisticasDeleteDialogComponent {
  estadisticas?: IEstadisticas;

  constructor(
    protected estadisticasService: EstadisticasService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.estadisticasService.delete(id).subscribe(() => {
      this.eventManager.broadcast('estadisticasListModification');
      this.activeModal.close();
    });
  }
}
