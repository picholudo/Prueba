import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { ActivatedRoute, Router, ParamMap } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IResultadoPrueba, ResultadoPrueba } from 'app/shared/model/resultado-prueba.model';
import { ResultadoPruebaService } from './resultado-prueba.service';
import { IPrueba } from 'app/shared/model/prueba.model';
import { PruebaService } from 'app/entities/prueba/prueba.service';
import { IInforme } from 'app/shared/model/informe.model';
import { InformeService } from 'app/entities/informe/informe.service';

type SelectableEntity = IPrueba | IInforme;

@Component({
  selector: 'jhi-resultado-prueba-update',
  templateUrl: './resultado-prueba-update.component.html'
})
export class ResultadoPruebaUpdateComponent implements OnInit {
  isSaving = false;

  pruebas: IPrueba[] = [];

  informes: IInforme[] = [];
  informeIdParam = 0;
  selectedInforme: IInforme | never[] = {};

  editForm = this.fb.group({
    id: [],
    pd: [null, [Validators.min(0)]],
    pz: [],
    pruebaId: [null, Validators.required],
    informeId: [null, Validators.required]
  });

  constructor(
    protected resultadoPruebaService: ResultadoPruebaService,
    protected pruebaService: PruebaService,
    protected informeService: InformeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
  
	 this.activatedRoute.queryParams.subscribe(params => {
	    this.informeIdParam = params['informeId'];
	    
	    if(this.informeIdParam > 0) {
	    	    this.informeService
	        	.find(this.informeIdParam)
	        	.pipe(
	          		map((res: HttpResponse<IInforme>) => {
	            	return res.body ? res.body : [];
	          })
	        )
	        .subscribe((resBody: IInforme | never[]) => (this.selectedInforme = resBody));
	    }
	  });
  
  
    this.activatedRoute.data.subscribe(({ resultadoPrueba }) => {
      
      this.updateForm(resultadoPrueba);
      this.pruebaService
        .query()
        .pipe(
          map((res: HttpResponse<IPrueba[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IPrueba[]) => (this.pruebas = resBody));

      this.informeService
        .query()
        .pipe(
          map((res: HttpResponse<IInforme[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IInforme[]) => (this.informes = resBody));
        

       if(this.informeIdParam > 0 && this.editForm.get('informeId') != null) {
        	this.editForm.get('informeId').disable();
        }
        
    });
  }

  updateForm(resultadoPrueba: IResultadoPrueba): void {
    this.editForm.patchValue({
      id: resultadoPrueba.id,
      pd: resultadoPrueba.pd,
      pz: resultadoPrueba.pz,
      pruebaId: resultadoPrueba.pruebaId,
      informeId: resultadoPrueba.informeId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const resultadoPrueba = this.createFromForm();
    if (resultadoPrueba.id !== undefined) {
      this.subscribeToSaveResponse(this.resultadoPruebaService.update(resultadoPrueba));
    } else {
      this.subscribeToSaveResponse(this.resultadoPruebaService.create(resultadoPrueba));
    }
  }

  private createFromForm(): IResultadoPrueba {
    return {
      ...new ResultadoPrueba(),
      id: this.editForm.get(['id'])!.value,
      pd: this.editForm.get(['pd'])!.value,
      pz: this.editForm.get(['pz'])!.value,
      pruebaId: this.editForm.get(['pruebaId'])!.value,
      informeId: this.editForm.get(['informeId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IResultadoPrueba>>): void {
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
