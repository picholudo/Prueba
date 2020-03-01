import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEvaluacion } from 'app/shared/model/evaluacion.model';
import { EvaluacionService } from './evaluacion.service';
import { EvaluacionDeleteDialogComponent } from './evaluacion-delete-dialog.component';

@Component({
  selector: 'jhi-evaluacion',
  templateUrl: './evaluacion.component.html'
})
export class EvaluacionComponent implements OnInit, OnDestroy {
  evaluacions?: IEvaluacion[];
  eventSubscriber?: Subscription;

  constructor(protected evaluacionService: EvaluacionService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.evaluacionService.query().subscribe((res: HttpResponse<IEvaluacion[]>) => {
      this.evaluacions = res.body ? res.body : [];
    });
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInEvaluacions();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IEvaluacion): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInEvaluacions(): void {
    this.eventSubscriber = this.eventManager.subscribe('evaluacionListModification', () => this.loadAll());
  }

  delete(evaluacion: IEvaluacion): void {
    const modalRef = this.modalService.open(EvaluacionDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.evaluacion = evaluacion;
  }
}
