import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICodigoEstudio } from 'app/shared/model/codigo-estudio.model';
import { CodigoEstudioService } from './codigo-estudio.service';

@Component({
  templateUrl: './codigo-estudio-delete-dialog.component.html'
})
export class CodigoEstudioDeleteDialogComponent {
  codigoEstudio?: ICodigoEstudio;

  constructor(
    protected codigoEstudioService: CodigoEstudioService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.codigoEstudioService.delete(id).subscribe(() => {
      this.eventManager.broadcast('codigoEstudioListModification');
      this.activeModal.close();
    });
  }
}
