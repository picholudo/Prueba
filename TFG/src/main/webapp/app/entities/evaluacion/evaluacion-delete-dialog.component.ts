import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEvaluacion } from 'app/shared/model/evaluacion.model';
import { EvaluacionService } from './evaluacion.service';

@Component({
  templateUrl: './evaluacion-delete-dialog.component.html'
})
export class EvaluacionDeleteDialogComponent {
  evaluacion?: IEvaluacion;

  constructor(
    protected evaluacionService: EvaluacionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.evaluacionService.delete(id).subscribe(() => {
      this.eventManager.broadcast('evaluacionListModification');
      this.activeModal.close();
    });
  }
}
