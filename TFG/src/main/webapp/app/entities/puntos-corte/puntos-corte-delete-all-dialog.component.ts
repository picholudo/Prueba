import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPuntosCorte } from 'app/shared/model/puntos-corte.model';
import { PuntosCorteService } from './puntos-corte.service';

@Component({
  templateUrl: './puntos-corte-delete-all-dialog.component.html'
})
export class PuntosCorteDeleteAllDialogComponent {
  puntosCorte?: IPuntosCorte;

  constructor(
    protected puntosCorteService: PuntosCorteService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDeleteAll(): void {
    this.puntosCorteService.deleteAll();
      this.eventManager.broadcast('puntosCorteListModification');
      this.activeModal.close();

  }
}
