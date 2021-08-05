import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { EstadisticaAjusteNeuronormaDetailComponent } from 'app/entities/estadistica-ajuste-neuronorma/estadistica-ajuste-neuronorma-detail.component';
import { EstadisticaAjusteNeuronorma } from 'app/shared/model/estadistica-ajuste-neuronorma.model';

describe('Component Tests', () => {
  describe('EstadisticaAjusteNeuronorma Management Detail Component', () => {
    let comp: EstadisticaAjusteNeuronormaDetailComponent;
    let fixture: ComponentFixture<EstadisticaAjusteNeuronormaDetailComponent>;
    const route = ({ data: of({ estadisticaAjusteNeuronorma: new EstadisticaAjusteNeuronorma(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [EstadisticaAjusteNeuronormaDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(EstadisticaAjusteNeuronormaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EstadisticaAjusteNeuronormaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load estadisticaAjusteNeuronorma on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.estadisticaAjusteNeuronorma).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
