import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { EstadisticaPzNeuronormaDetailComponent } from 'app/entities/estadistica-pz-neuronorma/estadistica-pz-neuronorma-detail.component';
import { EstadisticaPzNeuronorma } from 'app/shared/model/estadistica-pz-neuronorma.model';

describe('Component Tests', () => {
  describe('EstadisticaPzNeuronorma Management Detail Component', () => {
    let comp: EstadisticaPzNeuronormaDetailComponent;
    let fixture: ComponentFixture<EstadisticaPzNeuronormaDetailComponent>;
    const route = ({ data: of({ estadisticaPzNeuronorma: new EstadisticaPzNeuronorma(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [EstadisticaPzNeuronormaDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(EstadisticaPzNeuronormaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EstadisticaPzNeuronormaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load estadisticaPzNeuronorma on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.estadisticaPzNeuronorma).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
