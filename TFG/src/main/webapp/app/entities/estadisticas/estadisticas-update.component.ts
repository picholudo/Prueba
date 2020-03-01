import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IEstadisticas, Estadisticas } from 'app/shared/model/estadisticas.model';
import { EstadisticasService } from './estadisticas.service';
import { IZScore } from 'app/shared/model/z-score.model';
import { ZScoreService } from 'app/entities/z-score/z-score.service';

@Component({
  selector: 'jhi-estadisticas-update',
  templateUrl: './estadisticas-update.component.html'
})
export class EstadisticasUpdateComponent implements OnInit {
  isSaving = false;

  zscores: IZScore[] = [];

  editForm = this.fb.group({
    id: [],
    edad: [null, [Validators.min(60), Validators.max(120)]],
    estudios: [null, [Validators.required]],
    sexo: [null, [Validators.required]],
    prueba: [null, [Validators.required]],
    media: [null, [Validators.required]],
    desviacion: [null, [Validators.required]],
    zscoreId: [null, Validators.required]
  });

  constructor(
    protected estadisticasService: EstadisticasService,
    protected zScoreService: ZScoreService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ estadisticas }) => {
      this.updateForm(estadisticas);

      this.zScoreService
        .query()
        .pipe(
          map((res: HttpResponse<IZScore[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IZScore[]) => (this.zscores = resBody));
    });
  }

  updateForm(estadisticas: IEstadisticas): void {
    this.editForm.patchValue({
      id: estadisticas.id,
      edad: estadisticas.edad,
      estudios: estadisticas.estudios,
      sexo: estadisticas.sexo,
      prueba: estadisticas.prueba,
      media: estadisticas.media,
      desviacion: estadisticas.desviacion,
      zscoreId: estadisticas.zscoreId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const estadisticas = this.createFromForm();
    if (estadisticas.id !== undefined) {
      this.subscribeToSaveResponse(this.estadisticasService.update(estadisticas));
    } else {
      this.subscribeToSaveResponse(this.estadisticasService.create(estadisticas));
    }
  }

  private createFromForm(): IEstadisticas {
    return {
      ...new Estadisticas(),
      id: this.editForm.get(['id'])!.value,
      edad: this.editForm.get(['edad'])!.value,
      estudios: this.editForm.get(['estudios'])!.value,
      sexo: this.editForm.get(['sexo'])!.value,
      prueba: this.editForm.get(['prueba'])!.value,
      media: this.editForm.get(['media'])!.value,
      desviacion: this.editForm.get(['desviacion'])!.value,
      zscoreId: this.editForm.get(['zscoreId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEstadisticas>>): void {
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

  trackById(index: number, item: IZScore): any {
    return item.id;
  }
}
