import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IResultadoPrueba } from 'app/shared/model/resultado-prueba.model';
import { ResultadoPruebaService } from './resultado-prueba.service';
import { ResultadoPruebaDeleteDialogComponent } from './resultado-prueba-delete-dialog.component';

@Component({
  selector: 'jhi-resultado-prueba',
  templateUrl: './resultado-prueba.component.html'
})
export class ResultadoPruebaComponent implements OnInit, OnDestroy {
  resultadoPruebas?: IResultadoPrueba[];
  eventSubscriber?: Subscription;

  constructor(
    protected resultadoPruebaService: ResultadoPruebaService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.resultadoPruebaService.query().subscribe((res: HttpResponse<IResultadoPrueba[]>) => {
      this.resultadoPruebas = res.body ? res.body : [];
    });
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInResultadoPruebas();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IResultadoPrueba): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInResultadoPruebas(): void {
    this.eventSubscriber = this.eventManager.subscribe('resultadoPruebaListModification', () => this.loadAll());
  }

  delete(resultadoPrueba: IResultadoPrueba): void {
    const modalRef = this.modalService.open(ResultadoPruebaDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.resultadoPrueba = resultadoPrueba;
  }
}
