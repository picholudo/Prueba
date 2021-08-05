import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IResultadoPrueba } from 'app/shared/model/resultado-prueba.model';
import { ResultadoPruebaService } from './resultado-prueba.service';

@Component({
  templateUrl: './resultado-prueba-delete-dialog.component.html'
})
export class ResultadoPruebaDeleteDialogComponent {
  resultadoPrueba?: IResultadoPrueba;

  constructor(
    protected resultadoPruebaService: ResultadoPruebaService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.resultadoPruebaService.delete(id).subscribe(() => {
      this.eventManager.broadcast('resultadoPruebaListModification');
      this.activeModal.close();
    });
  }
}
