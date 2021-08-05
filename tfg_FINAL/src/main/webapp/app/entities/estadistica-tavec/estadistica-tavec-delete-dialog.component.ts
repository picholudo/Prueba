import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEstadisticaTAVEC } from 'app/shared/model/estadistica-tavec.model';
import { EstadisticaTAVECService } from './estadistica-tavec.service';

@Component({
  templateUrl: './estadistica-tavec-delete-dialog.component.html'
})
export class EstadisticaTAVECDeleteDialogComponent {
  estadisticaTAVEC?: IEstadisticaTAVEC;

  constructor(
    protected estadisticaTAVECService: EstadisticaTAVECService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.estadisticaTAVECService.delete(id).subscribe(() => {
      this.eventManager.broadcast('estadisticaTAVECListModification');
      this.activeModal.close();
    });
  }
}
