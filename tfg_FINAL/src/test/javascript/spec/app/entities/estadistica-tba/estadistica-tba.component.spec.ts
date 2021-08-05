import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AppTestModule } from '../../../test.module';
import { EstadisticaTBAComponent } from 'app/entities/estadistica-tba/estadistica-tba.component';
import { EstadisticaTBAService } from 'app/entities/estadistica-tba/estadistica-tba.service';
import { EstadisticaTBA } from 'app/shared/model/estadistica-tba.model';

describe('Component Tests', () => {
  describe('EstadisticaTBA Management Component', () => {
    let comp: EstadisticaTBAComponent;
    let fixture: ComponentFixture<EstadisticaTBAComponent>;
    let service: EstadisticaTBAService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [EstadisticaTBAComponent],
        providers: []
      })
        .overrideTemplate(EstadisticaTBAComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EstadisticaTBAComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EstadisticaTBAService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new EstadisticaTBA(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.estadisticaTBAS && comp.estadisticaTBAS[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
