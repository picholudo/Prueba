import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IEstadisticaMMSE, EstadisticaMMSE } from 'app/shared/model/estadistica-mmse.model';
import { EstadisticaMMSEService } from './estadistica-mmse.service';
import { ICodigoEstudio } from 'app/shared/model/codigo-estudio.model';
import { CodigoEstudioService } from 'app/entities/codigo-estudio/codigo-estudio.service';
import { IEdadTipoPrueba } from 'app/shared/model/edad-tipo-prueba.model';
import { EdadTipoPruebaService } from 'app/entities/edad-tipo-prueba/edad-tipo-prueba.service';

type SelectableEntity = ICodigoEstudio | IEdadTipoPrueba;

@Component({
  selector: 'jhi-estadistica-mmse-update',
  templateUrl: './estadistica-mmse-update.component.html'
})
export class EstadisticaMMSEUpdateComponent implements OnInit {
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
    protected estadisticaMMSEService: EstadisticaMMSEService,
    protected codigoEstudioService: CodigoEstudioService,
    protected edadTipoPruebaService: EdadTipoPruebaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ estadisticaMMSE }) => {
      this.updateForm(estadisticaMMSE);

      this.codigoEstudioService
        .query()
        .pipe(
          map((res: HttpResponse<ICodigoEstudio[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: ICodigoEstudio[]) => (this.codigoestudios = resBody));

      this.edadTipoPruebaService
        .query({tipoPrueba: 'MMSE'})
        .pipe(
          map((res: HttpResponse<IEdadTipoPrueba[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IEdadTipoPrueba[]) => (this.edadtipopruebas = resBody));
    });
  }

  updateForm(estadisticaMMSE: IEstadisticaMMSE): void {
    this.editForm.patchValue({
      id: estadisticaMMSE.id,
      media: estadisticaMMSE.media,
      desviacionTipica: estadisticaMMSE.desviacionTipica,
      codigoEstudioId: estadisticaMMSE.codigoEstudioId,
      edadTipoPruebaId: estadisticaMMSE.edadTipoPruebaId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const estadisticaMMSE = this.createFromForm();
    if (estadisticaMMSE.id !== undefined) {
      this.subscribeToSaveResponse(this.estadisticaMMSEService.update(estadisticaMMSE));
    } else {
      this.subscribeToSaveResponse(this.estadisticaMMSEService.create(estadisticaMMSE));
    }
  }

  private createFromForm(): IEstadisticaMMSE {
    return {
      ...new EstadisticaMMSE(),
      id: this.editForm.get(['id'])!.value,
      media: this.editForm.get(['media'])!.value,
      desviacionTipica: this.editForm.get(['desviacionTipica'])!.value,
      codigoEstudioId: this.editForm.get(['codigoEstudioId'])!.value,
      edadTipoPruebaId: this.editForm.get(['edadTipoPruebaId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEstadisticaMMSE>>): void {
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
