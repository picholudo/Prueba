import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICodigoEstudio, CodigoEstudio } from 'app/shared/model/codigo-estudio.model';
import { CodigoEstudioService } from './codigo-estudio.service';

@Component({
  selector: 'jhi-codigo-estudio-update',
  templateUrl: './codigo-estudio-update.component.html'
})
export class CodigoEstudioUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nivelEstudios: [null, [Validators.required]],
    codigo: [null, [Validators.required]]
  });

  constructor(protected codigoEstudioService: CodigoEstudioService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ codigoEstudio }) => {
      this.updateForm(codigoEstudio);
    });
  }

  updateForm(codigoEstudio: ICodigoEstudio): void {
    this.editForm.patchValue({
      id: codigoEstudio.id,
      nivelEstudios: codigoEstudio.nivelEstudios,
      codigo: codigoEstudio.codigo
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const codigoEstudio = this.createFromForm();
    if (codigoEstudio.id !== undefined) {
      this.subscribeToSaveResponse(this.codigoEstudioService.update(codigoEstudio));
    } else {
      this.subscribeToSaveResponse(this.codigoEstudioService.create(codigoEstudio));
    }
  }

  private createFromForm(): ICodigoEstudio {
    return {
      ...new CodigoEstudio(),
      id: this.editForm.get(['id'])!.value,
      nivelEstudios: this.editForm.get(['nivelEstudios'])!.value,
      codigo: this.editForm.get(['codigo'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICodigoEstudio>>): void {
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
