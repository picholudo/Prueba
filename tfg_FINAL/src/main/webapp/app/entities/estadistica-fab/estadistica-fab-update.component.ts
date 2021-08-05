import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IEstadisticaFAB, EstadisticaFAB } from 'app/shared/model/estadistica-fab.model';
import { EstadisticaFABService } from './estadistica-fab.service';
import { ICodigoEstudio } from 'app/shared/model/codigo-estudio.model';
import { CodigoEstudioService } from 'app/entities/codigo-estudio/codigo-estudio.service';
import { IEdadTipoPrueba } from 'app/shared/model/edad-tipo-prueba.model';
import { EdadTipoPruebaService } from 'app/entities/edad-tipo-prueba/edad-tipo-prueba.service';

type SelectableEntity = ICodigoEstudio | IEdadTipoPrueba;

@Component({
  selector: 'jhi-estadistica-fab-update',
  templateUrl: './estadistica-fab-update.component.html'
})
export class EstadisticaFABUpdateComponent implements OnInit {
  isSaving = false;

  codigoestudios: ICodigoEstudio[] = [];

  edadtipopruebas: IEdadTipoPrueba[] = [];

  editForm = this.fb.group({
    id: [],
    media: [null, [Validators.required, Validators.min(0)]],
    desviacionTipica: [null, [Validators.required, Validators.min(0)]],
    codigoEstudioId: [null, Validators.required],
    edadTipoPruebaId: [null, Validators.required]
  });

  constructor(
    protected estadisticaFABService: EstadisticaFABService,
    protected codigoEstudioService: CodigoEstudioService,
    protected edadTipoPruebaService: EdadTipoPruebaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ estadisticaFAB }) => {
      this.updateForm(estadisticaFAB);

      this.codigoEstudioService
        .query()
        .pipe(
          map((res: HttpResponse<ICodigoEstudio[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: ICodigoEstudio[]) => (this.codigoestudios = resBody));

      this.edadTipoPruebaService
        .query({tipoPrueba: 'FAB'})
        .pipe(
          map((res: HttpResponse<IEdadTipoPrueba[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IEdadTipoPrueba[]) => (this.edadtipopruebas = resBody));
    });
  }

  updateForm(estadisticaFAB: IEstadisticaFAB): void {
    this.editForm.patchValue({
      id: estadisticaFAB.id,
      media: estadisticaFAB.media,
      desviacionTipica: estadisticaFAB.desviacionTipica,
      codigoEstudioId: estadisticaFAB.codigoEstudioId,
      edadTipoPruebaId: estadisticaFAB.edadTipoPruebaId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const estadisticaFAB = this.createFromForm();
    if (estadisticaFAB.id !== undefined) {
      this.subscribeToSaveResponse(this.estadisticaFABService.update(estadisticaFAB));
    } else {
      this.subscribeToSaveResponse(this.estadisticaFABService.create(estadisticaFAB));
    }
  }

  private createFromForm(): IEstadisticaFAB {
    return {
      ...new EstadisticaFAB(),
      id: this.editForm.get(['id'])!.value,
      media: this.editForm.get(['media'])!.value,
      desviacionTipica: this.editForm.get(['desviacionTipica'])!.value,
      codigoEstudioId: this.editForm.get(['codigoEstudioId'])!.value,
      edadTipoPruebaId: this.editForm.get(['edadTipoPruebaId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEstadisticaFAB>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
