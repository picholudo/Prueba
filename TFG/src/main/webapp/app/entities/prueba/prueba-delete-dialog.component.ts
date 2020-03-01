import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPrueba } from 'app/shared/model/prueba.model';
import { PruebaService } from './prueba.service';

@Component({
  templateUrl: './prueba-delete-dialog.component.html'
})
export class PruebaDeleteDialogComponent {
  prueba?: IPrueba;

  constructor(protected pruebaService: PruebaService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.pruebaService.delete(id).subscribe(() => {
      this.eventManager.broadcast('pruebaListModification');
      this.activeModal.close();
    });
  }
}
