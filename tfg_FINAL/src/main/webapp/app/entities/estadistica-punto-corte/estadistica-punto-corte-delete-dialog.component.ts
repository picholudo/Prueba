import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEstadisticaPuntoCorte } from 'app/shared/model/estadistica-punto-corte.model';
import { EstadisticaPuntoCorteService } from './estadistica-punto-corte.service';

@Component({
  templateUrl: './estadistica-punto-corte-delete-dialog.component.html'
})
export class EstadisticaPuntoCorteDeleteDialogComponent {
  estadisticaPuntoCorte?: IEstadisticaPuntoCorte;

  constructor(
    protected estadisticaPuntoCorteService: EstadisticaPuntoCorteService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.estadisticaPuntoCorteService.delete(id).subscribe(() => {
      this.eventManager.broadcast('estadisticaPuntoCorteListModification');
      this.activeModal.close();
    });
  }
}
