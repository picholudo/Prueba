import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { ActivatedRoute, Router, ParamMap } from '@angular/router';

import { IInforme } from 'app/shared/model/informe.model';
import { InformeService } from './informe.service';
import { InformeDeleteDialogComponent } from './informe-delete-dialog.component';

@Component({
  selector: 'jhi-informe',
  templateUrl: './informe.component.html'
})
export class InformeComponent implements OnInit, OnDestroy {
  informes?: IInforme[];
  eventSubscriber?: Subscription;
  pacienteIdParam?: number;
  
  

  constructor(
  	protected informeService: InformeService, 
  	protected eventManager: JhiEventManager,
  	protected activatedRoute: ActivatedRoute, 
  	protected modalService: NgbModal
  	) {}

  loadAll(): void {
  	 this.activatedRoute.queryParams.subscribe(params => {
  	 	this.pacienteIdParam = params['pacienteId'];
  	 	
  	 	if(this.pacienteIdParam) {
			this.informeService
	    		.query({pacienteId: this.pacienteIdParam})
	    		.subscribe((res: HttpResponse<IInforme[]>) => {
	      	this.informes = res.body ? res.body : [];
	    	});  	 	
  	 	} else {
			this.informeService
	    		.query()
	    		.subscribe((res: HttpResponse<IInforme[]>) => {
	      		this.informes = res.body ? res.body : [];
	    	});  	 	
  	 	}
  	 	
  	 	
	    
     });
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInInformes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IInforme): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInInformes(): void {
    this.eventSubscriber = this.eventManager.subscribe('informeListModification', () => this.loadAll());
  }

  delete(informe: IInforme): void {
    const modalRef = this.modalService.open(InformeDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.informe = informe;
  }
}
