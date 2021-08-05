import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEstadisticaFAB } from 'app/shared/model/estadistica-fab.model';
import { EstadisticaFABService } from './estadistica-fab.service';

@Component({
  templateUrl: './estadistica-fab-delete-dialog.component.html'
})
export class EstadisticaFABDeleteDialogComponent {
  estadisticaFAB?: IEstadisticaFAB;

  constructor(
    protected estadisticaFABService: EstadisticaFABService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.estadisticaFABService.delete(id).subscribe(() => {
      this.eventManager.broadcast('estadisticaFABListModification');
      this.activeModal.close();
    });
  }
}
