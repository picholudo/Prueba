import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEdadTipoPrueba } from 'app/shared/model/edad-tipo-prueba.model';
import { EdadTipoPruebaService } from './edad-tipo-prueba.service';

@Component({
  templateUrl: './edad-tipo-prueba-delete-dialog.component.html'
})
export class EdadTipoPruebaDeleteDialogComponent {
  edadTipoPrueba?: IEdadTipoPrueba;

  constructor(
    protected edadTipoPruebaService: EdadTipoPruebaService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.edadTipoPruebaService.delete(id).subscribe(() => {
      this.eventManager.broadcast('edadTipoPruebaListModification');
      this.activeModal.close();
    });
  }
}
