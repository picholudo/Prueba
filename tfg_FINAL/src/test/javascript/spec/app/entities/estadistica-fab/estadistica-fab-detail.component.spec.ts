import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { EstadisticaFABDetailComponent } from 'app/entities/estadistica-fab/estadistica-fab-detail.component';
import { EstadisticaFAB } from 'app/shared/model/estadistica-fab.model';

describe('Component Tests', () => {
  describe('EstadisticaFAB Management Detail Component', () => {
    let comp: EstadisticaFABDetailComponent;
    let fixture: ComponentFixture<EstadisticaFABDetailComponent>;
    const route = ({ data: of({ estadisticaFAB: new EstadisticaFAB(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [EstadisticaFABDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(EstadisticaFABDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EstadisticaFABDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load estadisticaFAB on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.estadisticaFAB).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
