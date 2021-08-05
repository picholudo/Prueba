import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AppTestModule } from '../../../test.module';
import { EstadisticaTAVECComponent } from 'app/entities/estadistica-tavec/estadistica-tavec.component';
import { EstadisticaTAVECService } from 'app/entities/estadistica-tavec/estadistica-tavec.service';
import { EstadisticaTAVEC } from 'app/shared/model/estadistica-tavec.model';

describe('Component Tests', () => {
  describe('EstadisticaTAVEC Management Component', () => {
    let comp: EstadisticaTAVECComponent;
    let fixture: ComponentFixture<EstadisticaTAVECComponent>;
    let service: EstadisticaTAVECService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [EstadisticaTAVECComponent],
        providers: []
      })
        .overrideTemplate(EstadisticaTAVECComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EstadisticaTAVECComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EstadisticaTAVECService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new EstadisticaTAVEC(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.estadisticaTAVECS && comp.estadisticaTAVECS[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
