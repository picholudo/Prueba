import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IInforme } from 'app/shared/model/informe.model';
import { InformeService } from './informe.service';

@Component({
  templateUrl: './informe-delete-dialog.component.html'
})
export class InformeDeleteDialogComponent {
  informe?: IInforme;

  constructor(protected informeService: InformeService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.informeService.delete(id).subscribe(() => {
      this.eventManager.broadcast('informeListModification');
      this.activeModal.close();
    });
  }
}
