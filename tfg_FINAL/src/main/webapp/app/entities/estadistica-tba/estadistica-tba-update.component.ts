import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IEstadisticaTBA, EstadisticaTBA } from 'app/shared/model/estadistica-tba.model';
import { EstadisticaTBAService } from './estadistica-tba.service';
import { IPrueba } from 'app/shared/model/prueba.model';
import { PruebaService } from 'app/entities/prueba/prueba.service';
import { ICodigoEstudio } from 'app/shared/model/codigo-estudio.model';
import { CodigoEstudioService } from 'app/entities/codigo-estudio/codigo-estudio.service';
import { IEdadTipoPrueba } from 'app/shared/model/edad-tipo-prueba.model';
import { EdadTipoPruebaService } from 'app/entities/edad-tipo-prueba/edad-tipo-prueba.service';

type SelectableEntity = IPrueba | ICodigoEstudio | IEdadTipoPrueba;

@Component({
  selector: 'jhi-estadistica-tba-update',
  templateUrl: './estadistica-tba-update.component.html'
})
export class EstadisticaTBAUpdateComponent implements OnInit {
  isSaving = false;

  pruebas: IPrueba[] = [];

  codigoestudios: ICodigoEstudio[] = [];

  edadtipopruebas: IEdadTipoPrueba[] = [];

  editForm = this.fb.group({
    id: [],
    media: [null, [Validators.required, Validators.min(0)]],
    desviacionTipica: [null, [Validators.required, Validators.min(0)]],
    pruebaId: [null, Validators.required],
    codigoEstudioId: [null, Validators.required],
    edadTipoPruebaId: [null, Validators.required]
  });

  constructor(
    protected estadisticaTBAService: EstadisticaTBAService,
    protected pruebaService: PruebaService,
    protected codigoEstudioService: CodigoEstudioService,
    protected edadTipoPruebaService: EdadTipoPruebaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ estadisticaTBA }) => {
      this.updateForm(estadisticaTBA);

      this.pruebaService
        .query({tipoPrueba: 'TBA'})
        .pipe(
          map((res: HttpResponse<IPrueba[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IPrueba[]) => (this.pruebas = resBody));

      this.codigoEstudioService
        .query()
        .pipe(
          map((res: HttpResponse<ICodigoEstudio[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: ICodigoEstudio[]) => (this.codigoestudios = resBody));

      this.edadTipoPruebaService
        .query({tipoPrueba: 'TBA'})
        .pipe(
          map((res: HttpResponse<IEdadTipoPrueba[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IEdadTipoPrueba[]) => (this.edadtipopruebas = resBody));
    });
  }

  updateForm(estadisticaTBA: IEstadisticaTBA): void {
    this.editForm.patchValue({
      id: estadisticaTBA.id,
      media: estadisticaTBA.media,
      desviacionTipica: estadisticaTBA.desviacionTipica,
      pruebaId: estadisticaTBA.pruebaId,
      codigoEstudioId: estadisticaTBA.codigoEstudioId,
      edadTipoPruebaId: estadisticaTBA.edadTipoPruebaId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const estadisticaTBA = this.createFromForm();
    if (estadisticaTBA.id !== undefined) {
      this.subscribeToSaveResponse(this.estadisticaTBAService.update(estadisticaTBA));
    } else {
      this.subscribeToSaveResponse(this.estadisticaTBAService.create(estadisticaTBA));
    }
  }

  private createFromForm(): IEstadisticaTBA {
    return {
      ...new EstadisticaTBA(),
      id: this.editForm.get(['id'])!.value,
      media: this.editForm.get(['media'])!.value,
      desviacionTipica: this.editForm.get(['desviacionTipica'])!.value,
      pruebaId: this.editForm.get(['pruebaId'])!.value,
      codigoEstudioId: this.editForm.get(['codigoEstudioId'])!.value,
      edadTipoPruebaId: this.editForm.get(['edadTipoPruebaId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEstadisticaTBA>>): void {
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
