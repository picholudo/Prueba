import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IEstadisticaSSNeuronorma, EstadisticaSSNeuronorma } from 'app/shared/model/estadistica-ss-neuronorma.model';
import { EstadisticaSSNeuronormaService } from './estadistica-ss-neuronorma.service';
import { IPrueba } from 'app/shared/model/prueba.model';
import { PruebaService } from 'app/entities/prueba/prueba.service';
import { IEdadTipoPrueba } from 'app/shared/model/edad-tipo-prueba.model';
import { EdadTipoPruebaService } from 'app/entities/edad-tipo-prueba/edad-tipo-prueba.service';

type SelectableEntity = IPrueba | IEdadTipoPrueba;

@Component({
  selector: 'jhi-estadistica-ss-neuronorma-update',
  templateUrl: './estadistica-ss-neuronorma-update.component.html'
})
export class EstadisticaSSNeuronormaUpdateComponent implements OnInit {
  isSaving = false;

  pruebas: IPrueba[] = [];

  edadtipopruebas: IEdadTipoPrueba[] = [];

  editForm = this.fb.group({
    id: [],
    pd: [null, [Validators.required, Validators.min(0)]],
    scaledScore: [null, [Validators.required, Validators.min(0)]],
    pruebaId: [null, Validators.required],
    edadTipoPruebaId: [null, Validators.required]
  });

  constructor(
    protected estadisticaSSNeuronormaService: EstadisticaSSNeuronormaService,
    protected pruebaService: PruebaService,
    protected edadTipoPruebaService: EdadTipoPruebaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ estadisticaSSNeuronorma }) => {
      this.updateForm(estadisticaSSNeuronorma);

      this.pruebaService
        .query({tipoPrueba: 'NEURONORMA'})
        .pipe(
          map((res: HttpResponse<IPrueba[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IPrueba[]) => (this.pruebas = resBody));

      this.edadTipoPruebaService
        .query({tipoPrueba: 'NEURONORMA'})
        .pipe(
          map((res: HttpResponse<IEdadTipoPrueba[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IEdadTipoPrueba[]) => (this.edadtipopruebas = resBody));
    });
  }

  updateForm(estadisticaSSNeuronorma: IEstadisticaSSNeuronorma): void {
    this.editForm.patchValue({
      id: estadisticaSSNeuronorma.id,
      pd: estadisticaSSNeuronorma.pd,
      scaledScore: estadisticaSSNeuronorma.scaledScore,
      pruebaId: estadisticaSSNeuronorma.pruebaId,
      edadTipoPruebaId: estadisticaSSNeuronorma.edadTipoPruebaId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const estadisticaSSNeuronorma = this.createFromForm();
    if (estadisticaSSNeuronorma.id !== undefined) {
      this.subscribeToSaveResponse(this.estadisticaSSNeuronormaService.update(estadisticaSSNeuronorma));
    } else {
      this.subscribeToSaveResponse(this.estadisticaSSNeuronormaService.create(estadisticaSSNeuronorma));
    }
  }

  private createFromForm(): IEstadisticaSSNeuronorma {
    return {
      ...new EstadisticaSSNeuronorma(),
      id: this.editForm.get(['id'])!.value,
      pd: this.editForm.get(['pd'])!.value,
      scaledScore: this.editForm.get(['scaledScore'])!.value,
      pruebaId: this.editForm.get(['pruebaId'])!.value,
      edadTipoPruebaId: this.editForm.get(['edadTipoPruebaId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEstadisticaSSNeuronorma>>): void {
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
