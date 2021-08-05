import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IEdadTipoPrueba, EdadTipoPrueba } from 'app/shared/model/edad-tipo-prueba.model';
import { EdadTipoPruebaService } from './edad-tipo-prueba.service';

@Component({
  selector: 'jhi-edad-tipo-prueba-update',
  templateUrl: './edad-tipo-prueba-update.component.html'
})
export class EdadTipoPruebaUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    codigo: [null, [Validators.required]],
    edadMinima: [null, [Validators.required]],
    edadMaxima: [null, [Validators.required]],
    tipoPrueba: [null, [Validators.required]]
  });

  constructor(protected edadTipoPruebaService: EdadTipoPruebaService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ edadTipoPrueba }) => {
      this.updateForm(edadTipoPrueba);
    });
  }

  updateForm(edadTipoPrueba: IEdadTipoPrueba): void {
    this.editForm.patchValue({
      id: edadTipoPrueba.id,
      codigo: edadTipoPrueba.codigo,
      edadMinima: edadTipoPrueba.edadMinima,
      edadMaxima: edadTipoPrueba.edadMaxima,
      tipoPrueba: edadTipoPrueba.tipoPrueba
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const edadTipoPrueba = this.createFromForm();
    if (edadTipoPrueba.id !== undefined) {
      this.subscribeToSaveResponse(this.edadTipoPruebaService.update(edadTipoPrueba));
    } else {
      this.subscribeToSaveResponse(this.edadTipoPruebaService.create(edadTipoPrueba));
    }
  }

  private createFromForm(): IEdadTipoPrueba {
    return {
      ...new EdadTipoPrueba(),
      id: this.editForm.get(['id'])!.value,
      codigo: this.editForm.get(['codigo'])!.value,
      edadMinima: this.editForm.get(['edadMinima'])!.value,
      edadMaxima: this.editForm.get(['edadMaxima'])!.value,
      tipoPrueba: this.editForm.get(['tipoPrueba'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEdadTipoPrueba>>): void {
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
