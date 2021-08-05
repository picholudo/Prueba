import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { EdadTipoPruebaDetailComponent } from 'app/entities/edad-tipo-prueba/edad-tipo-prueba-detail.component';
import { EdadTipoPrueba } from 'app/shared/model/edad-tipo-prueba.model';

describe('Component Tests', () => {
  describe('EdadTipoPrueba Management Detail Component', () => {
    let comp: EdadTipoPruebaDetailComponent;
    let fixture: ComponentFixture<EdadTipoPruebaDetailComponent>;
    const route = ({ data: of({ edadTipoPrueba: new EdadTipoPrueba(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [EdadTipoPruebaDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(EdadTipoPruebaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EdadTipoPruebaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load edadTipoPrueba on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.edadTipoPrueba).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
