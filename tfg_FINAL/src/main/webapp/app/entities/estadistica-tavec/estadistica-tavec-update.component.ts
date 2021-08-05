import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IEstadisticaTAVEC, EstadisticaTAVEC } from 'app/shared/model/estadistica-tavec.model';
import { EstadisticaTAVECService } from './estadistica-tavec.service';
import { IPrueba } from 'app/shared/model/prueba.model';
import { PruebaService } from 'app/entities/prueba/prueba.service';
import { IEdadTipoPrueba } from 'app/shared/model/edad-tipo-prueba.model';
import { EdadTipoPruebaService } from 'app/entities/edad-tipo-prueba/edad-tipo-prueba.service';

type SelectableEntity = IPrueba | IEdadTipoPrueba;

@Component({
  selector: 'jhi-estadistica-tavec-update',
  templateUrl: './estadistica-tavec-update.component.html'
})
export class EstadisticaTAVECUpdateComponent implements OnInit {
  isSaving = false;

  pruebas: IPrueba[] = [];

  edadtipopruebas: IEdadTipoPrueba[] = [];

  editForm = this.fb.group({
    id: [],
    media: [null, [Validators.required, Validators.min(0)]],
    desviacionTipica: [null, [Validators.required, Validators.min(0)]],
    pruebaId: [null, Validators.required],
    edadTipoPruebaId: [null, Validators.required]
  });

  constructor(
    protected estadisticaTAVECService: EstadisticaTAVECService,
    protected pruebaService: PruebaService,
    protected edadTipoPruebaService: EdadTipoPruebaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ estadisticaTAVEC }) => {
      this.updateForm(estadisticaTAVEC);

      this.pruebaService
        .query({tipoPrueba: 'TAVEC'})
        .pipe(
          map((res: HttpResponse<IPrueba[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IPrueba[]) => (this.pruebas = resBody));

      this.edadTipoPruebaService
        .query({tipoPrueba: 'TAVEC'})
        .pipe(
          map((res: HttpResponse<IEdadTipoPrueba[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IEdadTipoPrueba[]) => (this.edadtipopruebas = resBody));
    });
  }

  updateForm(estadisticaTAVEC: IEstadisticaTAVEC): void {
    this.editForm.patchValue({
      id: estadisticaTAVEC.id,
      media: estadisticaTAVEC.media,
      desviacionTipica: estadisticaTAVEC.desviacionTipica,
      pruebaId: estadisticaTAVEC.pruebaId,
      edadTipoPruebaId: estadisticaTAVEC.edadTipoPruebaId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const estadisticaTAVEC = this.createFromForm();
    if (estadisticaTAVEC.id !== undefined) {
      this.subscribeToSaveResponse(this.estadisticaTAVECService.update(estadisticaTAVEC));
    } else {
      this.subscribeToSaveResponse(this.estadisticaTAVECService.create(estadisticaTAVEC));
    }
  }

  private createFromForm(): IEstadisticaTAVEC {
    return {
      ...new EstadisticaTAVEC(),
      id: this.editForm.get(['id'])!.value,
      media: this.editForm.get(['media'])!.value,
      desviacionTipica: this.editForm.get(['desviacionTipica'])!.value,
      pruebaId: this.editForm.get(['pruebaId'])!.value,
      edadTipoPruebaId: this.editForm.get(['edadTipoPruebaId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEstadisticaTAVEC>>): void {
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
