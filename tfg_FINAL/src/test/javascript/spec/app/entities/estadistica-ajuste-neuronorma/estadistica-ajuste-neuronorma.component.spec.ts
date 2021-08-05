import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AppTestModule } from '../../../test.module';
import { EstadisticaAjusteNeuronormaComponent } from 'app/entities/estadistica-ajuste-neuronorma/estadistica-ajuste-neuronorma.component';
import { EstadisticaAjusteNeuronormaService } from 'app/entities/estadistica-ajuste-neuronorma/estadistica-ajuste-neuronorma.service';
import { EstadisticaAjusteNeuronorma } from 'app/shared/model/estadistica-ajuste-neuronorma.model';

describe('Component Tests', () => {
  describe('EstadisticaAjusteNeuronorma Management Component', () => {
    let comp: EstadisticaAjusteNeuronormaComponent;
    let fixture: ComponentFixture<EstadisticaAjusteNeuronormaComponent>;
    let service: EstadisticaAjusteNeuronormaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [EstadisticaAjusteNeuronormaComponent],
        providers: []
      })
        .overrideTemplate(EstadisticaAjusteNeuronormaComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EstadisticaAjusteNeuronormaComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EstadisticaAjusteNeuronormaService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new EstadisticaAjusteNeuronorma(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.estadisticaAjusteNeuronormas && comp.estadisticaAjusteNeuronormas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
