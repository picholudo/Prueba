import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AppTestModule } from '../../../test.module';
import { EstadisticaPzNeuronormaComponent } from 'app/entities/estadistica-pz-neuronorma/estadistica-pz-neuronorma.component';
import { EstadisticaPzNeuronormaService } from 'app/entities/estadistica-pz-neuronorma/estadistica-pz-neuronorma.service';
import { EstadisticaPzNeuronorma } from 'app/shared/model/estadistica-pz-neuronorma.model';

describe('Component Tests', () => {
  describe('EstadisticaPzNeuronorma Management Component', () => {
    let comp: EstadisticaPzNeuronormaComponent;
    let fixture: ComponentFixture<EstadisticaPzNeuronormaComponent>;
    let service: EstadisticaPzNeuronormaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [EstadisticaPzNeuronormaComponent],
        providers: []
      })
        .overrideTemplate(EstadisticaPzNeuronormaComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EstadisticaPzNeuronormaComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EstadisticaPzNeuronormaService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new EstadisticaPzNeuronorma(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.estadisticaPzNeuronormas && comp.estadisticaPzNeuronormas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
