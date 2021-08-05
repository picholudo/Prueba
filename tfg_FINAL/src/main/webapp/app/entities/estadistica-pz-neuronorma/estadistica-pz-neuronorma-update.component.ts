import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IEstadisticaPzNeuronorma, EstadisticaPzNeuronorma } from 'app/shared/model/estadistica-pz-neuronorma.model';
import { EstadisticaPzNeuronormaService } from './estadistica-pz-neuronorma.service';

@Component({
  selector: 'jhi-estadistica-pz-neuronorma-update',
  templateUrl: './estadistica-pz-neuronorma-update.component.html'
})
export class EstadisticaPzNeuronormaUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    ajusteEstudios: [null, [Validators.required, Validators.min(0)]],
    pz: [null, [Validators.required]]
  });

  constructor(
    protected estadisticaPzNeuronormaService: EstadisticaPzNeuronormaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ estadisticaPzNeuronorma }) => {
      this.updateForm(estadisticaPzNeuronorma);
    });
  }

  updateForm(estadisticaPzNeuronorma: IEstadisticaPzNeuronorma): void {
    this.editForm.patchValue({
      id: estadisticaPzNeuronorma.id,
      ajusteEstudios: estadisticaPzNeuronorma.ajusteEstudios,
      pz: estadisticaPzNeuronorma.pz
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const estadisticaPzNeuronorma = this.createFromForm();
    if (estadisticaPzNeuronorma.id !== undefined) {
      this.subscribeToSaveResponse(this.estadisticaPzNeuronormaService.update(estadisticaPzNeuronorma));
    } else {
      this.subscribeToSaveResponse(this.estadisticaPzNeuronormaService.create(estadisticaPzNeuronorma));
    }
  }

  private createFromForm(): IEstadisticaPzNeuronorma {
    return {
      ...new EstadisticaPzNeuronorma(),
      id: this.editForm.get(['id'])!.value,
      ajusteEstudios: this.editForm.get(['ajusteEstudios'])!.value,
      pz: this.editForm.get(['pz'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEstadisticaPzNeuronorma>>): void {
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
