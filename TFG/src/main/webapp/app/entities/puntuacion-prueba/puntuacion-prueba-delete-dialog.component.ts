import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPuntuacionPrueba } from 'app/shared/model/puntuacion-prueba.model';
import { PuntuacionPruebaService } from './puntuacion-prueba.service';

@Component({
  templateUrl: './puntuacion-prueba-delete-dialog.component.html'
})
export class PuntuacionPruebaDeleteDialogComponent {
  puntuacionPrueba?: IPuntuacionPrueba;

  constructor(
    protected puntuacionPruebaService: PuntuacionPruebaService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.puntuacionPruebaService.delete(id).subscribe(() => {
      this.eventManager.broadcast('puntuacionPruebaListModification');
      this.activeModal.close();
    });
  }
}
