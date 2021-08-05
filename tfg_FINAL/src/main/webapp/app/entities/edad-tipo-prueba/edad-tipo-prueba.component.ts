import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEdadTipoPrueba } from 'app/shared/model/edad-tipo-prueba.model';
import { EdadTipoPruebaService } from './edad-tipo-prueba.service';
import { EdadTipoPruebaDeleteDialogComponent } from './edad-tipo-prueba-delete-dialog.component';

@Component({
  selector: 'jhi-edad-tipo-prueba',
  templateUrl: './edad-tipo-prueba.component.html'
})
export class EdadTipoPruebaComponent implements OnInit, OnDestroy {
  edadTipoPruebas?: IEdadTipoPrueba[];
  eventSubscriber?: Subscription;

  constructor(
    protected edadTipoPruebaService: EdadTipoPruebaService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.edadTipoPruebaService.query().subscribe((res: HttpResponse<IEdadTipoPrueba[]>) => {
      this.edadTipoPruebas = res.body ? res.body : [];
    });
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInEdadTipoPruebas();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IEdadTipoPrueba): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInEdadTipoPruebas(): void {
    this.eventSubscriber = this.eventManager.subscribe('edadTipoPruebaListModification', () => this.loadAll());
  }

  delete(edadTipoPrueba: IEdadTipoPrueba): void {
    const modalRef = this.modalService.open(EdadTipoPruebaDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.edadTipoPrueba = edadTipoPrueba;
  }
}
