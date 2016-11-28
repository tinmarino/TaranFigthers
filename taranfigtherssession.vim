let SessionLoad = 1
if &cp | set nocp | endif
let s:cpo_save=&cpo
set cpo&vim
inoremap <expr> <Down> pumvisible() ? "\" : "\<Down>"
inoremap <expr> <S-Tab> pumvisible() ? "\" : "\<S-Tab>"
inoremap <expr> <Up> pumvisible() ? "\" : "\<Up>"
inoremap <C-Space> 
imap <Nul> <C-Space>
inoremap <S-Down> :let tmp=getpos('.') :m+1 : call cursor(tmp[1]+1,tmp[2]) a
inoremap <S-Up> :let tmp=getpos('.') :m-2 : call cursor(tmp[1]-1,tmp[2]) a
vnoremap  "+p
nnoremap  "+p
nnoremap  :update
vnoremap  :update
onoremap  :update
vnoremap  "+y
nnoremap  "+y
vnoremap   zf
nnoremap   za
nnoremap ,d :YcmShowDetailedDiagnostic
nmap ,j <Plug>(CommandTJump)
nmap ,t <Plug>(CommandT)
map ,0 :10b
map ,9 :9b
map ,8 :8b
map ,7 :7b
map ,6 :6b
map ,5 :5b
map ,4 :4b
map ,3 :3b
map ,2 :2b
map ,1 :1b
map ,g :e#
map ,f :bn
map ,b :bp
map ,l :ls
noremap <silent> ,cu :silent s/^\V=escape(b:comment_leader,'\/')//e:nohlsearch
noremap <silent> ,cc :silent s/^/=escape(b:comment_leader,'\/')/:nohlsearch
map ,u :s,^\(\s*\)[^# \t]\@=// ,\1,gv
map ,c :s,^\(\s*\)[^# \t]\@=,\1// ,gv
vnoremap < <gv 
vnoremap > >gv 
nnoremap H :set cursorline! cursorcolumn! 
map [1;2C <S-Right>
map [1;2D <S-Left>
map [1;5C <C-Right>
map [1;5D <C-Left>
map [1;5B <C-Down>
map [1;5A <C-Up>
nmap gx <Plug>NetrwBrowseX
map xy :s/x/y/g
map yx :s/y/x/g
nnoremap <silent> <Plug>NetrwBrowseX :call netrw#NetrwBrowseX(expand("<cWORD>"),0)
nnoremap <SNR>63_: :=v:count ? v:count : ''
nnoremap <silent> <Plug>(CommandTJump) :CommandTJump
nnoremap <silent> <Plug>(CommandTBuffer) :CommandTBuffer
nnoremap <silent> <Plug>(CommandT) :CommandT
nnoremap <F10> :b 
noremap <C-Tab> :BufExplorer
vnoremap <silent> <C-Down> zM
nnoremap <silent> <C-Down> zM
vnoremap <silent> <C-Up> zR
nnoremap <silent> <C-Up> zR
vnoremap <silent> <C-Left> zm
nnoremap <silent> <C-Left> zm
vnoremap <silent> <C-Right> zr
nnoremap <silent> <C-Right> zr
vnoremap <S-Down> :m '>+1gv
vnoremap <S-Up> :m '<-2gv
nnoremap <S-Up> :let tmp=getpos('.') :m-2 : call cursor(tmp[1]-1,tmp[2]) 
nnoremap <S-Down> :let tmp=getpos('.') :m+1 : call cursor(tmp[1]+1,tmp[2]) 
inoremap  yiW<End>==0
inoremap <expr> 	 pumvisible() ? "\" : "\	"
inoremap  :update
cnoremap %% =expand('%:p:h')
cmap [1;2C <S-Right>
cmap [1;2D <S-Left>
imap jk 
imap kj 
let &cpo=s:cpo_save
unlet s:cpo_save
set autoindent
set autoread
set backspace=2
set backupdir=~/.vim/backup//
set balloonexpr=eclim#util#Balloon(eclim#util#GetLineError(line('.')))
set completefunc=youcompleteme#Complete
set completeopt=preview,menuone
set cpoptions=aAceFsB
set directory=~/.vim/backup//
set fileencodings=ucs-bom,utf-8,default,latin1
set foldlevelstart=30
set helplang=en
set history=100000
set hlsearch
set incsearch
set mouse=a
set path=~/Software/Java/Libgdx/Jar/Source/libgdx-nightly-20160329/sources
set printoptions=paper:a4
set ruler
set runtimepath=~/.vim,~/.vim/bundle/L9,~/.vim/bundle/Vundle.vim,~/.vim/bundle/YouCompleteMe,~/.vim/bundle/command-t,~/.vim/bundle/nerdtree,~/.vim/bundle/python-mode,~/.vim/bundle/sparkup,~/.vim/bundle/tabular,~/.vim/bundle/vim-colorscheme-switcher,~/.vim/bundle/vim-fugitive,~/.vim/bundle/vim-misc,~/.vim/bundle/vis,/var/lib/vim/addons,/usr/share/vim/vimfiles,/usr/share/vim/vim74,/usr/share/vim/vimfiles/after,/var/lib/vim/addons/after,~/.vim/bundle/python-mode/after,~/.vim/after,~/.vim/eclim,~/.vim/eclim/after
set shiftwidth=4
set smartcase
set suffixes=.bak,~,.swp,.o,.info,.aux,.log,.dvi,.bbl,.blg,.brf,.cb,.ind,.idx,.ilg,.inx,.out,.toc,.class
set noswapfile
set tabstop=4
set title
set undodir=~/.vim/undo
set undofile
set undolevels=100000
set updatetime=2000
set wildcharm=26
set wildmenu
set window=54
set nowritebackup
let s:so_save = &so | let s:siso_save = &siso | set so=0 siso=0
let v:this_session=expand("<sfile>:p")
silent only
cd ~/Software/Java/Libgdx/TaranFigthers
if expand('%') == '' && !&modified && line('$') <= 1 && getline(1) == ''
  let s:wipebuf = bufnr('%')
endif
set shortmess=aoO
badd +15 core/src/com/mygdx/taranfighters/MainGdx.java
badd +71 core/src/com/mygdx/taranfighters/CombatScreen.java
badd +19 .classpath
badd +46 core/src/com/mygdx/taranfighters/Iul.java
badd +154 core/src/com/mygdx/taranfighters/Character.java
badd +10 core/src/com/mygdx/taranfighters/SpriteChanging.java
badd +7 core/src/com/mygdx/taranfighters/TextureTime.java
badd +19 ~/Software/Java/Libgdx/Jar/Source/libgdx-nightly-20160329/sources/com/badlogic/gdx/graphics/g2d/Sprite.java
badd +52 ~/Software/Java/Libgdx/Jar/Source/libgdx-nightly-20160329/sources/com/badlogic/gdx/InputProcessor.java
badd +85 ~/Software/Java/Libgdx/Jar/Source/libgdx-nightly-20160329/sources/com/badlogic/gdx/graphics/Texture.java
badd +12 core/src/com/mygdx/taranfighters/G.java
badd +16 ~/Software/Java/Libgdx/Jar/Source/libgdx-nightly-20160329/sources/com/badlogic/gdx/maps/tiled/TiledMapRenderer.java
badd +55 ~/Software/Java/Libgdx/Jar/Source/libgdx-nightly-20160329/sources/com/badlogic/gdx/maps/tiled/renderers/OrthogonalTiledMapRenderer.java
badd +65 ~/Software/Java/Libgdx/Jar/Source/libgdx-nightly-20160329/sources/com/badlogic/gdx/maps/tiled/renderers/BatchTiledMapRenderer.java
badd +27 core/src/com/mygdx/taranfighters/Level.java
badd +27 ~/Software/Java/Libgdx/Jar/Source/libgdx-nightly-20160329/sources/com/badlogic/gdx/math/Vector2.java
badd +109 core/src/com/mygdx/taranfighters/MapBodyBuilder.java
badd +28 ~/Software/Java/Libgdx/Jar/Source/libgdx-nightly-20160329/sources/com/badlogic/gdx/maps/MapObjects.java
badd +69 core/src/com/mygdx/taranfighters/Rat.java
badd +23 todo
badd +37 core/src/com/mygdx/taranfighters/JakOverScreen.java
badd +2 ~/Software/Java/Libgdx/Jar/Source/libgdx-nightly-20160329/sources/com/badlogic/gdx/graphics/g2d/TextureAtlas.java
badd +22 ~/Software/Java/Libgdx/Jar/Source/libgdx-nightly-20160329/sources/com/badlogic/gdx/ApplicationAdapter.java
badd +21 ~/Software/Java/Libgdx/Jar/Source/libgdx-nightly-20160329/sources/com/badlogic/gdx/graphics/Color.java
badd +14 desktop/src/com/mygdx/taranfighters/desktop/DesktopLauncher.java
badd +15 html/src/com/mygdx/taranfighters/client/HtmlLauncher.java
badd +98 ~/Software/Java/Libgdx/Jar/Source/libgdx-nightly-20160329/sources/com/badlogic/gdx/graphics/g2d/SpriteBatch.java
badd +13 core/src/com/mygdx/taranfighters/Jak.java
args core/src/com/mygdx/taranfighters/MainGdx.java
edit core/src/com/mygdx/taranfighters/CombatScreen.java
set splitbelow splitright
wincmd _ | wincmd |
vsplit
1wincmd h
wincmd w
wincmd _ | wincmd |
split
1wincmd k
wincmd w
set nosplitbelow
set nosplitright
wincmd t
set winheight=1 winwidth=1
exe 'vert 1resize ' . ((&columns * 100 + 92) / 185)
exe '2resize ' . ((&lines * 3 + 27) / 55)
exe 'vert 2resize ' . ((&columns * 84 + 92) / 185)
exe '3resize ' . ((&lines * 49 + 27) / 55)
exe 'vert 3resize ' . ((&columns * 84 + 92) / 185)
argglobal
inoreabbr <buffer> logger logger=eclim#java#logging#LoggingInit("logger")
inoreabbr <buffer> log log=eclim#java#logging#LoggingInit("log")
setlocal keymap=
setlocal noarabic
setlocal autoindent
setlocal balloonexpr=
setlocal nobinary
setlocal bufhidden=
setlocal buflisted
setlocal buftype=
setlocal cindent
setlocal cinkeys=0{,0},0),:,0#,!^F,o,O,e
setlocal cinoptions=j1
setlocal cinwords=if,else,while,do,for,switch
setlocal colorcolumn=
setlocal comments=sO:*\ -,mO:*\ \ ,exO:*/,s1:/*,mb:*,ex:*/,://,b:#,:%,:XCOMM,n:>,fb:-
setlocal commentstring=//%s
setlocal complete=.,w,b,u,t,i
setlocal concealcursor=
setlocal conceallevel=0
setlocal completefunc=youcompleteme#Complete
setlocal nocopyindent
setlocal cryptmethod=
setlocal nocursorbind
setlocal nocursorcolumn
set cursorline
setlocal cursorline
setlocal define=
setlocal dictionary=
setlocal nodiff
setlocal equalprg=
setlocal errorformat=%E%f:%l:\ %m,%-Z%p^,%-C%.%#,%-G%.%#
setlocal noexpandtab
if &filetype != 'java'
setlocal filetype=java
endif
setlocal foldcolumn=0
setlocal foldenable
set foldexpr=FoldMethod(v:lnum)
setlocal foldexpr=FoldMethod(v:lnum)
setlocal foldignore=#
setlocal foldlevel=30
setlocal foldmarker={{{,}}}
set foldmethod=expr
setlocal foldmethod=expr
setlocal foldminlines=1
setlocal foldnestmax=20
setlocal foldtext=foldtext()
setlocal formatexpr=
setlocal formatoptions=croql
setlocal formatlistpat=^\\s*\\d\\+[\\]:.)}\\t\ ]\\s*
setlocal grepprg=
setlocal iminsert=2
setlocal imsearch=2
setlocal include=^s*import
setlocal includeexpr=substitute(v:fname,'\\.','/','g')
setlocal indentexpr=GetJavaIndent()
setlocal indentkeys=0{,0},:,0#,!^F,o,O,e,0=extends,0=implements
setlocal noinfercase
setlocal iskeyword=@,48-57,_,192-255
setlocal keywordprg=
setlocal nolinebreak
setlocal nolisp
setlocal nolist
setlocal makeprg=javac
setlocal matchpairs=(:),{:},[:]
setlocal modeline
setlocal modifiable
setlocal nrformats=octal,hex
set number
setlocal number
setlocal numberwidth=4
setlocal omnifunc=eclim#java#complete#CodeComplete
setlocal path=
setlocal nopreserveindent
setlocal nopreviewwindow
setlocal quoteescape=\\
setlocal noreadonly
setlocal norelativenumber
setlocal norightleft
setlocal rightleftcmd=search
setlocal noscrollbind
setlocal shiftwidth=4
setlocal noshortname
setlocal nosmartindent
setlocal softtabstop=0
setlocal nospell
setlocal spellcapcheck=[.?!]\\_[\\])'\"\	\ ]\\+
setlocal spellfile=
setlocal spelllang=en
setlocal statusline=
setlocal suffixesadd=.java
setlocal noswapfile
setlocal synmaxcol=3000
if &syntax != 'java'
setlocal syntax=java
endif
setlocal tabstop=4
setlocal tags=
setlocal textwidth=0
setlocal thesaurus=
setlocal undofile
setlocal nowinfixheight
setlocal nowinfixwidth
setlocal wrap
setlocal wrapmargin=0
22
normal! zo
62
normal! zo
85
normal! zo
112
normal! zo
114
normal! zo
175
normal! zo
240
normal! zo
241
normal! zo
243
normal! zo
301
normal! zo
let s:l = 231 - ((49 * winheight(0) + 26) / 53)
if s:l < 1 | let s:l = 1 | endif
exe s:l
normal! zt
231
normal! 018|
wincmd w
argglobal
enew
setlocal keymap=
setlocal noarabic
setlocal autoindent
setlocal balloonexpr=
setlocal nobinary
setlocal bufhidden=wipe
setlocal buflisted
setlocal buftype=nofile
setlocal nocindent
setlocal cinkeys=0{,0},0),:,0#,!^F,o,O,e
setlocal cinoptions=
setlocal cinwords=if,else,while,do,for,switch
setlocal colorcolumn=
setlocal comments=s1:/*,mb:*,ex:*/,://,b:#,:%,:XCOMM,n:>,fb:-
setlocal commentstring=/*%s*/
setlocal complete=.,w,b,u,t,i
setlocal concealcursor=
setlocal conceallevel=0
setlocal completefunc=youcompleteme#Complete
setlocal nocopyindent
setlocal cryptmethod=
setlocal nocursorbind
setlocal nocursorcolumn
set cursorline
setlocal cursorline
setlocal define=
setlocal dictionary=
setlocal nodiff
setlocal equalprg=
setlocal errorformat=
setlocal noexpandtab
if &filetype != ''
setlocal filetype=
endif
setlocal foldcolumn=0
setlocal foldenable
set foldexpr=FoldMethod(v:lnum)
setlocal foldexpr=FoldMethod(v:lnum)
setlocal foldignore=#
setlocal foldlevel=30
setlocal foldmarker={{{,}}}
set foldmethod=expr
setlocal foldmethod=expr
setlocal foldminlines=1
setlocal foldnestmax=20
setlocal foldtext=foldtext()
setlocal formatexpr=
setlocal formatoptions=tcq
setlocal formatlistpat=^\\s*\\d\\+[\\]:.)}\\t\ ]\\s*
setlocal grepprg=
setlocal iminsert=2
setlocal imsearch=2
setlocal include=
setlocal includeexpr=
setlocal indentexpr=
setlocal indentkeys=0{,0},:,0#,!^F,o,O,e
setlocal noinfercase
setlocal iskeyword=@,48-57,_,192-255
setlocal keywordprg=
setlocal nolinebreak
setlocal nolisp
setlocal nolist
setlocal makeprg=
setlocal matchpairs=(:),{:},[:]
setlocal modeline
setlocal nomodifiable
setlocal nrformats=octal,hex
set number
setlocal number
setlocal numberwidth=4
setlocal omnifunc=
setlocal path=
setlocal nopreserveindent
setlocal previewwindow
setlocal quoteescape=\\
setlocal noreadonly
setlocal norelativenumber
setlocal norightleft
setlocal rightleftcmd=search
setlocal noscrollbind
setlocal shiftwidth=4
setlocal noshortname
setlocal nosmartindent
setlocal softtabstop=0
setlocal nospell
setlocal spellcapcheck=[.?!]\\_[\\])'\"\	\ ]\\+
setlocal spellfile=
setlocal spelllang=en
setlocal statusline=
setlocal suffixesadd=
setlocal noswapfile
setlocal synmaxcol=3000
if &syntax != ''
setlocal syntax=
endif
setlocal tabstop=4
setlocal tags=
setlocal textwidth=0
setlocal thesaurus=
setlocal undofile
setlocal winfixheight
setlocal nowinfixwidth
setlocal wrap
setlocal wrapmargin=0
wincmd w
argglobal
edit todo
setlocal keymap=
setlocal noarabic
setlocal autoindent
setlocal balloonexpr=
setlocal nobinary
setlocal bufhidden=
setlocal buflisted
setlocal buftype=
setlocal nocindent
setlocal cinkeys=0{,0},0),:,0#,!^F,o,O,e
setlocal cinoptions=
setlocal cinwords=if,else,while,do,for,switch
setlocal colorcolumn=
setlocal comments=s1:/*,mb:*,ex:*/,://,b:#,:%,:XCOMM,n:>,fb:-
setlocal commentstring=/*%s*/
setlocal complete=.,w,b,u,t,i
setlocal concealcursor=
setlocal conceallevel=0
setlocal completefunc=youcompleteme#Complete
setlocal nocopyindent
setlocal cryptmethod=
setlocal nocursorbind
setlocal nocursorcolumn
set cursorline
setlocal cursorline
setlocal define=
setlocal dictionary=
setlocal nodiff
setlocal equalprg=
setlocal errorformat=
setlocal noexpandtab
if &filetype != ''
setlocal filetype=
endif
setlocal foldcolumn=0
setlocal foldenable
set foldexpr=FoldMethod(v:lnum)
setlocal foldexpr=FoldMethod(v:lnum)
setlocal foldignore=#
setlocal foldlevel=30
setlocal foldmarker={{{,}}}
set foldmethod=expr
setlocal foldmethod=expr
setlocal foldminlines=1
setlocal foldnestmax=20
setlocal foldtext=foldtext()
setlocal formatexpr=
setlocal formatoptions=tcq
setlocal formatlistpat=^\\s*\\d\\+[\\]:.)}\\t\ ]\\s*
setlocal grepprg=
setlocal iminsert=2
setlocal imsearch=2
setlocal include=
setlocal includeexpr=
setlocal indentexpr=
setlocal indentkeys=0{,0},:,0#,!^F,o,O,e
setlocal noinfercase
setlocal iskeyword=@,48-57,_,192-255
setlocal keywordprg=
setlocal nolinebreak
setlocal nolisp
setlocal nolist
setlocal makeprg=
setlocal matchpairs=(:),{:},[:]
setlocal modeline
setlocal modifiable
setlocal nrformats=octal,hex
set number
setlocal number
setlocal numberwidth=4
setlocal omnifunc=
setlocal path=
setlocal nopreserveindent
setlocal nopreviewwindow
setlocal quoteescape=\\
setlocal noreadonly
setlocal norelativenumber
setlocal norightleft
setlocal rightleftcmd=search
setlocal noscrollbind
setlocal shiftwidth=4
setlocal noshortname
setlocal nosmartindent
setlocal softtabstop=0
setlocal nospell
setlocal spellcapcheck=[.?!]\\_[\\])'\"\	\ ]\\+
setlocal spellfile=
setlocal spelllang=en
setlocal statusline=
setlocal suffixesadd=
setlocal noswapfile
setlocal synmaxcol=3000
if &syntax != ''
setlocal syntax=
endif
setlocal tabstop=4
setlocal tags=
setlocal textwidth=0
setlocal thesaurus=
setlocal undofile
setlocal nowinfixheight
setlocal nowinfixwidth
setlocal wrap
setlocal wrapmargin=0
16
normal! zo
16
normal! zo
let s:l = 19 - ((18 * winheight(0) + 24) / 49)
if s:l < 1 | let s:l = 1 | endif
exe s:l
normal! zt
19
normal! 0142|
wincmd w
3wincmd w
exe 'vert 1resize ' . ((&columns * 100 + 92) / 185)
exe '2resize ' . ((&lines * 3 + 27) / 55)
exe 'vert 2resize ' . ((&columns * 84 + 92) / 185)
exe '3resize ' . ((&lines * 49 + 27) / 55)
exe 'vert 3resize ' . ((&columns * 84 + 92) / 185)
tabnext 1
if exists('s:wipebuf')
  silent exe 'bwipe ' . s:wipebuf
endif
unlet! s:wipebuf
set winheight=1 winwidth=20 shortmess=filnxtToO
let s:sx = expand("<sfile>:p:r")."x.vim"
if file_readable(s:sx)
  exe "source " . fnameescape(s:sx)
endif
let &so = s:so_save | let &siso = s:siso_save
doautoall SessionLoadPost
unlet SessionLoad
" vim: set ft=vim :
