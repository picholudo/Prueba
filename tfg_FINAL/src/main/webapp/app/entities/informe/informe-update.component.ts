import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { Router, ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
// eslint-disable-next-line @typescript-eslint/no-unused-vars

import { IInforme, Informe } from 'app/shared/model/informe.model';
import { InformeService } from './informe.service';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';
import { IPaciente } from 'app/shared/model/paciente.model';
import { PacienteService } from 'app/entities/paciente/paciente.service';
import { IResultadoPrueba } from 'app/shared/model/resultado-prueba.model';
import { ResultadoPruebaService } from 'app/entities/resultado-prueba/resultado-prueba.service';
import { AccountService } from 'app/core/auth/account.service';
import { Account } from 'app/core/user/account.model';

type SelectableEntity = IUser | IPaciente;

@Component({
  selector: 'jhi-informe-update',
  templateUrl: './informe-update.component.html'
})
export class InformeUpdateComponent implements OnInit {
  isSaving = false;
  isHome = false;

  users: IUser[] = [];

  pacientes: IPaciente[] = [];
  newInforme?: IInforme;
  resultadoPruebas?: IResultadoPrueba[];
  fechaEvaluacionDp: any;
  account: Account | null = null;
  sospechaClinicaSugerida: any;

  editForm = this.fb.group({
    id: [],
    sospechaClinica: [],
    sospechaClinicaSugerida:[],
    fechaEvaluacion: [null, [Validators.required]],
    motivoConsulta: [],
    orientacion: [],
    memoria: [],
    praxias: [],
    lenguaje: [],
    funcionesEjecutivas: [],
    conducta: [],
    actividadesDiarias: [],
    resumen: [],
    userId: [null, Validators.required],
    pacienteId: [null, Validators.required]
  });

  constructor(
    protected informeService: InformeService,
    protected userService: UserService,
    protected pacienteService: PacienteService,
    protected resultadoPruebaService: ResultadoPruebaService,
    protected accountService: AccountService,
    protected activatedRoute: ActivatedRoute,
    private router:Router,
    private fb: FormBuilder
  ) {}
  
  loadResultadosPrueba(informe : IInforme): void {
    this.resultadoPruebaService.query({informeId: informe.id}).subscribe((res: HttpResponse<IResultadoPrueba[]>) => {
      this.resultadoPruebas = res.body ? res.body : [];
    });
  }

  ngOnInit(): void {
  
     
	 this.activatedRoute.queryParams.subscribe(params => {
	    this.isHome = params['home'];
	  });  
  
    this.activatedRoute.data.subscribe(({ informe }) => {     
    
    
    
     this.updateForm(informe);
      
	 this.accountService.identity().subscribe(account => {
	  if (account) {
	    
	    if(informe.id === undefined) { 
		    this.editForm.patchValue({
		      userId: account.id
		    });	    
	    }
	            
	    this.account = account;
	  }
	});      	
     
      this.userService
        .query()
        .pipe(
          map((res: HttpResponse<IUser[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IUser[]) => (this.users = resBody));

      this.pacienteService
        .query()
        .pipe(
          map((res: HttpResponse<IPaciente[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IPaciente[]) => (this.pacientes = resBody));
        
		if(informe.id !== undefined) {
			this.loadResultadosPrueba(informe);
		}        
    });

  }

  updateForm(informe: IInforme): void {
    this.editForm.patchValue({
      id: informe.id,
      sospechaClinica: informe.sospechaClinica,
      sospechaClinicaSugerida: informe.sospechaClinicaSugerida,
      fechaEvaluacion: informe.fechaEvaluacion,
      motivoConsulta: informe.motivoConsulta,
      orientacion: informe.orientacion,
      memoria: informe.memoria,
      praxias: informe.praxias,
      lenguaje: informe.lenguaje,
      funcionesEjecutivas: informe.funcionesEjecutivas,
      conducta: informe.conducta,
      actividadesDiarias: informe.actividadesDiarias,
      resumen: informe.resumen,
      userId: informe.userId,
      pacienteId: informe.pacienteId
    });
  }
  
  getSuggestedDiagnosis(): void {
    this.informeService.getDiagnosis().subscribe(
      res=> {
      this.sospechaClinicaSugerida =  res
      console.log(this.sospechaClinicaSugerida)
    },
      err => {
        console.log(err);
      }
    );
    
  }
  previousState(): void {
    window.history.back();
  }
  
  
  save(): void {
    this.isSaving = true;
    const informe = this.createFromForm();
    const isUpdate = informe.id !== undefined;
    
    this.subscribeToSaveResponse(isUpdate ? this.informeService.update(informe) : this.informeService.create(informe), isUpdate);
  }

  private createFromForm(): IInforme {
    return {
      ...new Informe(),
      id: this.editForm.get(['id'])!.value,
      sospechaClinica: this.editForm.get(['sospechaClinica'])!.value,
      sospechaClinicaSugerida: this.editForm.get(['sospechaClinicaSugerida'])!.value,
      fechaEvaluacion: this.editForm.get(['fechaEvaluacion'])!.value,
      motivoConsulta: this.editForm.get(['motivoConsulta'])!.value,
      orientacion: this.editForm.get(['orientacion'])!.value,
      memoria: this.editForm.get(['memoria'])!.value,
      praxias: this.editForm.get(['praxias'])!.value,
      lenguaje: this.editForm.get(['lenguaje'])!.value,
      funcionesEjecutivas: this.editForm.get(['funcionesEjecutivas'])!.value,
      conducta: this.editForm.get(['conducta'])!.value,
      actividadesDiarias: this.editForm.get(['actividadesDiarias'])!.value,
      resumen: this.editForm.get(['resumen'])!.value,
      userId: this.editForm.get(['userId'])!.value,
      pacienteId: this.editForm.get(['pacienteId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IInforme>>, isUpdate: boolean): void {
  	result
  		.pipe(map(res => { return res.body}))
  		.subscribe(
		      	(informe) => this.onSaveSuccess(informe, isUpdate),
      			() => this.onSaveError()
    );
  }

  protected onSaveSuccess(informe : IInforme | null, isUpdate: boolean): void {
    this.isSaving = false;
    
    if(informe == null || isUpdate) {
        this.router.navigate([ this.isHome ? '/' : 'informe']);
    } else {
    	this.router.navigate(['informe', informe.id, 'edit'], {
    		queryParams: { home: this.isHome }
    	});
    }
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
