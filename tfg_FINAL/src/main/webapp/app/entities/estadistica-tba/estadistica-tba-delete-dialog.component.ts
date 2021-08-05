import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEstadisticaTBA } from 'app/shared/model/estadistica-tba.model';
import { EstadisticaTBAService } from './estadistica-tba.service';

@Component({
  templateUrl: './estadistica-tba-delete-dialog.component.html'
})
export class EstadisticaTBADeleteDialogComponent {
  estadisticaTBA?: IEstadisticaTBA;

  constructor(
    protected estadisticaTBAService: EstadisticaTBAService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.estadisticaTBAService.delete(id).subscribe(() => {
      this.eventManager.broadcast('estadisticaTBAListModification');
      this.activeModal.close();
    });
  }
}
