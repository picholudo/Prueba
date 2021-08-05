import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IEstadisticaAjusteNeuronorma, EstadisticaAjusteNeuronorma } from 'app/shared/model/estadistica-ajuste-neuronorma.model';
import { EstadisticaAjusteNeuronormaService } from './estadistica-ajuste-neuronorma.service';
import { IPrueba } from 'app/shared/model/prueba.model';
import { PruebaService } from 'app/entities/prueba/prueba.service';
import { ICodigoEstudio } from 'app/shared/model/codigo-estudio.model';
import { CodigoEstudioService } from 'app/entities/codigo-estudio/codigo-estudio.service';

type SelectableEntity = IPrueba | ICodigoEstudio;

@Component({
  selector: 'jhi-estadistica-ajuste-neuronorma-update',
  templateUrl: './estadistica-ajuste-neuronorma-update.component.html'
})
export class EstadisticaAjusteNeuronormaUpdateComponent implements OnInit {
  isSaving = false;

  pruebas: IPrueba[] = [];

  codigoestudios: ICodigoEstudio[] = [];

  editForm = this.fb.group({
    id: [],
    scaledScore: [null, [Validators.required, Validators.min(0)]],
    ajusteEstudios: [null, [Validators.required, Validators.min(0)]],
    pruebaId: [null, Validators.required],
    codigoEstudioId: [null, Validators.required]
  });

  constructor(
    protected estadisticaAjusteNeuronormaService: EstadisticaAjusteNeuronormaService,
    protected pruebaService: PruebaService,
    protected codigoEstudioService: CodigoEstudioService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ estadisticaAjusteNeuronorma }) => {
      this.updateForm(estadisticaAjusteNeuronorma);

      this.pruebaService
        .query({tipoPrueba: 'NEURONORMA'})
        .pipe(
          map((res: HttpResponse<IPrueba[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IPrueba[]) => (this.pruebas = resBody));

      this.codigoEstudioService
        .query({tipoPrueba: 'NEURONORMA'})
        .pipe(
          map((res: HttpResponse<ICodigoEstudio[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: ICodigoEstudio[]) => (this.codigoestudios = resBody));
    });
  }

  updateForm(estadisticaAjusteNeuronorma: IEstadisticaAjusteNeuronorma): void {
    this.editForm.patchValue({
      id: estadisticaAjusteNeuronorma.id,
      scaledScore: estadisticaAjusteNeuronorma.scaledScore,
      ajusteEstudios: estadisticaAjusteNeuronorma.ajusteEstudios,
      pruebaId: estadisticaAjusteNeuronorma.pruebaId,
      codigoEstudioId: estadisticaAjusteNeuronorma.codigoEstudioId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const estadisticaAjusteNeuronorma = this.createFromForm();
    if (estadisticaAjusteNeuronorma.id !== undefined) {
      this.subscribeToSaveResponse(this.estadisticaAjusteNeuronormaService.update(estadisticaAjusteNeuronorma));
    } else {
      this.subscribeToSaveResponse(this.estadisticaAjusteNeuronormaService.create(estadisticaAjusteNeuronorma));
    }
  }

  private createFromForm(): IEstadisticaAjusteNeuronorma {
    return {
      ...new EstadisticaAjusteNeuronorma(),
      id: this.editForm.get(['id'])!.value,
      scaledScore: this.editForm.get(['scaledScore'])!.value,
      ajusteEstudios: this.editForm.get(['ajusteEstudios'])!.value,
      pruebaId: this.editForm.get(['pruebaId'])!.value,
      codigoEstudioId: this.editForm.get(['codigoEstudioId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEstadisticaAjusteNeuronorma>>): void {
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
