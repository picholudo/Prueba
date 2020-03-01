import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { IEvaluacion, Evaluacion } from 'app/shared/model/evaluacion.model';
import { EvaluacionService } from './evaluacion.service';
import { IPaciente } from 'app/shared/model/paciente.model';
import { PacienteService } from 'app/entities/paciente/paciente.service';

@Component({
  selector: 'jhi-evaluacion-update',
  templateUrl: './evaluacion-update.component.html'
})
export class EvaluacionUpdateComponent implements OnInit {
  isSaving = false;

  pacientes: IPaciente[] = [];
  fechaDp: any;

  editForm = this.fb.group({
    id: [],
    fecha: [null, [Validators.required]],
    valoracion: [null, [Validators.required]],
    pacienteId: [null, Validators.required]
  });

  constructor(
    protected evaluacionService: EvaluacionService,
    protected pacienteService: PacienteService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ evaluacion }) => {
      this.updateForm(evaluacion);

      this.pacienteService
        .query()
        .pipe(
          map((res: HttpResponse<IPaciente[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IPaciente[]) => (this.pacientes = resBody));
    });
  }

  updateForm(evaluacion: IEvaluacion): void {
    this.editForm.patchValue({
      id: evaluacion.id,
      fecha: evaluacion.fecha,
      valoracion: evaluacion.valoracion,
      pacienteId: evaluacion.pacienteId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const evaluacion = this.createFromForm();
    if (evaluacion.id !== undefined) {
      this.subscribeToSaveResponse(this.evaluacionService.update(evaluacion));
    } else {
      this.subscribeToSaveResponse(this.evaluacionService.create(evaluacion));
    }
  }

  private createFromForm(): IEvaluacion {
    return {
      ...new Evaluacion(),
      id: this.editForm.get(['id'])!.value,
      fecha: this.editForm.get(['fecha'])!.value,
      valoracion: this.editForm.get(['valoracion'])!.value,
      pacienteId: this.editForm.get(['pacienteId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEvaluacion>>): void {
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

  trackById(index: number, item: IPaciente): any {
    return item.id;
  }
}
