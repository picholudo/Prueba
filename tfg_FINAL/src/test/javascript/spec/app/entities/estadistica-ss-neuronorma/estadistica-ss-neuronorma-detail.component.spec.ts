import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { EstadisticaSSNeuronormaDetailComponent } from 'app/entities/estadistica-ss-neuronorma/estadistica-ss-neuronorma-detail.component';
import { EstadisticaSSNeuronorma } from 'app/shared/model/estadistica-ss-neuronorma.model';

describe('Component Tests', () => {
  describe('EstadisticaSSNeuronorma Management Detail Component', () => {
    let comp: EstadisticaSSNeuronormaDetailComponent;
    let fixture: ComponentFixture<EstadisticaSSNeuronormaDetailComponent>;
    const route = ({ data: of({ estadisticaSSNeuronorma: new EstadisticaSSNeuronorma(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [EstadisticaSSNeuronormaDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(EstadisticaSSNeuronormaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EstadisticaSSNeuronormaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load estadisticaSSNeuronorma on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.estadisticaSSNeuronorma).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
