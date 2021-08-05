import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AppTestModule } from '../../../test.module';
import { EstadisticaSSNeuronormaComponent } from 'app/entities/estadistica-ss-neuronorma/estadistica-ss-neuronorma.component';
import { EstadisticaSSNeuronormaService } from 'app/entities/estadistica-ss-neuronorma/estadistica-ss-neuronorma.service';
import { EstadisticaSSNeuronorma } from 'app/shared/model/estadistica-ss-neuronorma.model';

describe('Component Tests', () => {
  describe('EstadisticaSSNeuronorma Management Component', () => {
    let comp: EstadisticaSSNeuronormaComponent;
    let fixture: ComponentFixture<EstadisticaSSNeuronormaComponent>;
    let service: EstadisticaSSNeuronormaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [EstadisticaSSNeuronormaComponent],
        providers: []
      })
        .overrideTemplate(EstadisticaSSNeuronormaComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EstadisticaSSNeuronormaComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EstadisticaSSNeuronormaService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new EstadisticaSSNeuronorma(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.estadisticaSSNeuronormas && comp.estadisticaSSNeuronormas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
