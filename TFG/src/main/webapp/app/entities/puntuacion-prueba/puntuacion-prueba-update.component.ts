import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IPuntuacionPrueba, PuntuacionPrueba } from 'app/shared/model/puntuacion-prueba.model';
import { PuntuacionPruebaService } from './puntuacion-prueba.service';
import { IZScore } from 'app/shared/model/z-score.model';
import { ZScoreService } from 'app/entities/z-score/z-score.service';
import { IEvaluacion } from 'app/shared/model/evaluacion.model';
import { EvaluacionService } from 'app/entities/evaluacion/evaluacion.service';

type SelectableEntity = IZScore | IEvaluacion;

@Component({
  selector: 'jhi-puntuacion-prueba-update',
  templateUrl: './puntuacion-prueba-update.component.html'
})
export class PuntuacionPruebaUpdateComponent implements OnInit {
  isSaving = false;

  zscores: IZScore[] = [];

  evaluacions: IEvaluacion[] = [];

  editForm = this.fb.group({
    id: [],
    valor: [],
    zscoreId: [null, Validators.required],
    pacienteId: [null, Validators.required]
  });

  constructor(
    protected puntuacionPruebaService: PuntuacionPruebaService,
    protected zScoreService: ZScoreService,
    protected evaluacionService: EvaluacionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ puntuacionPrueba }) => {
      this.updateForm(puntuacionPrueba);

      this.zScoreService
        .query()
        .pipe(
          map((res: HttpResponse<IZScore[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IZScore[]) => (this.zscores = resBody));

      this.evaluacionService
        .query()
        .pipe(
          map((res: HttpResponse<IEvaluacion[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IEvaluacion[]) => (this.evaluacions = resBody));
    });
  }

  updateForm(puntuacionPrueba: IPuntuacionPrueba): void {
    this.editForm.patchValue({
      id: puntuacionPrueba.id,
      valor: puntuacionPrueba.valor,
      zscoreId: puntuacionPrueba.zscoreId,
      pacienteId: puntuacionPrueba.pacienteId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const puntuacionPrueba = this.createFromForm();
    if (puntuacionPrueba.id !== undefined) {
      this.subscribeToSaveResponse(this.puntuacionPruebaService.update(puntuacionPrueba));
    } else {
      this.subscribeToSaveResponse(this.puntuacionPruebaService.create(puntuacionPrueba));
    }
  }

  private createFromForm(): IPuntuacionPrueba {
    return {
      ...new PuntuacionPrueba(),
      id: this.editForm.get(['id'])!.value,
      valor: this.editForm.get(['valor'])!.value,
      zscoreId: this.editForm.get(['zscoreId'])!.value,
      pacienteId: this.editForm.get(['pacienteId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPuntuacionPrueba>>): void {
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
