import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IPaciente, Paciente } from 'app/shared/model/paciente.model';
import { PacienteService } from './paciente.service';

@Component({
  selector: 'jhi-paciente-update',
  templateUrl: './paciente-update.component.html'
})
export class PacienteUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nhc: [null, [Validators.required]],
    nombre: [null, [Validators.required]],
    sexo: [null, [Validators.required]],
    profesion: [],
    estudios: [null, [Validators.required]],
    edad: [null, [Validators.required, Validators.min(0)]]
  });

  constructor(protected pacienteService: PacienteService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ paciente }) => {
      this.updateForm(paciente);
    });
  }

  updateForm(paciente: IPaciente): void {
    this.editForm.patchValue({
      id: paciente.id,
      nhc: paciente.nhc,
      nombre: paciente.nombre,
      sexo: paciente.sexo,
      profesion: paciente.profesion,
      estudios: paciente.estudios,
      edad: paciente.edad
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const paciente = this.createFromForm();
    if (paciente.id !== undefined) {
      this.subscribeToSaveResponse(this.pacienteService.update(paciente));
    } else {
      this.subscribeToSaveResponse(this.pacienteService.create(paciente));
    }
  }

  private createFromForm(): IPaciente {
    return {
      ...new Paciente(),
      id: this.editForm.get(['id'])!.value,
      nhc: this.editForm.get(['nhc'])!.value,
      nombre: this.editForm.get(['nombre'])!.value,
      sexo: this.editForm.get(['sexo'])!.value,
      profesion: this.editForm.get(['profesion'])!.value,
      estudios: this.editForm.get(['estudios'])!.value,
      edad: this.editForm.get(['edad'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPaciente>>): void {
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
}
