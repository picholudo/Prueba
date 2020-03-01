import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IPrueba, Prueba } from 'app/shared/model/prueba.model';
import { PruebaService } from './prueba.service';

@Component({
  selector: 'jhi-prueba-update',
  templateUrl: './prueba-update.component.html'
})
export class PruebaUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nombre: [null, [Validators.required]]
  });

  constructor(protected pruebaService: PruebaService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ prueba }) => {
      this.updateForm(prueba);
    });
  }

  updateForm(prueba: IPrueba): void {
    this.editForm.patchValue({
      id: prueba.id,
      nombre: prueba.nombre
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const prueba = this.createFromForm();
    if (prueba.id !== undefined) {
      this.subscribeToSaveResponse(this.pruebaService.update(prueba));
    } else {
      this.subscribeToSaveResponse(this.pruebaService.create(prueba));
    }
  }

  private createFromForm(): IPrueba {
    return {
      ...new Prueba(),
      id: this.editForm.get(['id'])!.value,
      nombre: this.editForm.get(['nombre'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPrueba>>): void {
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
